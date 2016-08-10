package im.r_c.android.blogm.data.source;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import im.r_c.android.blogm.App;
import im.r_c.android.blogm.data.model.Archive;
import im.r_c.android.blogm.data.model.CustomPage;
import im.r_c.android.blogm.data.model.Post;
import im.r_c.android.blogm.data.model.PostList;
import im.r_c.android.blogm.data.model.Site;
import im.r_c.android.fusioncache.FusionCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class RemoteDataSource implements DataSource {

    private String mBaseUrl;

    @Inject
    OkHttpClient mHttpClient;

    @Inject
    Gson mGson;

    @Inject
    FusionCache mCache;

    public RemoteDataSource(String baseUrl) {
        mBaseUrl = baseUrl;
        App.getNetComponent().inject(this);
    }

    @Override
    public Observable<PostList> getPostList(String relUrl) {
        return Observable.just(relUrl)
                .map(relativeUrl -> mBaseUrl + relativeUrl)
                .map(url -> fetchDataPage(url, PostList.class));
    }

    @Override
    public Observable<Post> getPost(String relUrl) {
        return Observable.just(relUrl)
                .map(relativeUrl -> mBaseUrl + relativeUrl)
                .map(url -> fetchDataPage(url, Post.class));
    }

    @Override
    public Observable<CustomPage> getCustomPage(String relUrl) {
        return Observable.just(relUrl)
                .map(relativeUrl -> mBaseUrl + relativeUrl)
                .map(url -> fetchDataPage(url, CustomPage.class));
    }

    @Override
    public Observable<Archive> getArchive(String relUrl) {
        return Observable.just(relUrl)
                .map(relativeUrl -> mBaseUrl + relativeUrl)
                .map(url -> fetchDataPage(url, Archive.class));
    }

    @Override
    public Observable<Site> getSite() {
        return Observable.just("/")
                .map(relativeUrl -> mBaseUrl + relativeUrl)
                .map(url -> {
                    Site site = null;
                    try {
                        Response response = mHttpClient.newCall(new Request.Builder()
                                .url(url)
                                .build())
                                .execute();
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getBoolean("ok")) {
                            String siteJsonString = jsonObject.getJSONObject("site").toString();
                            Observable.just(siteJsonString)
                                    .subscribeOn(Schedulers.io())
                                    .forEach(jsonStr -> mCache.getDiskCache().put("site", jsonStr));
                            site = mGson.fromJson(siteJsonString, Site.class);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    return site;
                });
    }

    private <T> T fetchDataPage(String url, Class<T> clz) {
        try {
            Response response = mHttpClient.newCall(new Request.Builder()
                    .url(url)
                    .build())
                    .execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            if (jsonObject.getBoolean("ok")) {
                String pageJsonString = jsonObject.getJSONObject("page").toString();
                Observable.just(pageJsonString)
                        .subscribeOn(Schedulers.io())
                        .forEach(jsonStr -> mCache.getDiskCache().put(url, jsonStr));
                return mGson.fromJson(pageJsonString, clz);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
