package im.r_c.android.blogm.data.source;

import com.google.gson.Gson;

import javax.inject.Inject;

import im.r_c.android.blogm.App;
import im.r_c.android.blogm.data.model.Archive;
import im.r_c.android.blogm.data.model.CustomPage;
import im.r_c.android.blogm.data.model.Post;
import im.r_c.android.blogm.data.model.PostList;
import im.r_c.android.blogm.data.model.Site;
import im.r_c.android.fusioncache.FusionCache;
import rx.Observable;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class LocalDataSource implements DataSource {

    private String mBaseUrl;

    @Inject
    FusionCache mCache;

    @Inject
    Gson mGson;

    public LocalDataSource(String baseUrl) {
        mBaseUrl = baseUrl;
        App.getToolComponent().inject(this);
    }

    @Override
    public Observable<PostList> getPostList(String relUrl) {
        return fetchDataPage(relUrl, PostList.class);
    }

    @Override
    public Observable<Post> getPost(String relUrl) {
        return fetchDataPage(relUrl, Post.class);
    }

    @Override
    public Observable<CustomPage> getCustomPage(String relUrl) {
        return fetchDataPage(relUrl, CustomPage.class);
    }

    @Override
    public Observable<Archive> getArchive(String relUrl) {
        return fetchDataPage(relUrl, Archive.class);
    }

    @Override
    public Observable<Site> getSite() {
        return Observable.just("site")
                .map(key -> {
                    String jsonStr = mCache.getString(key);
                    if (jsonStr == null) {
                        return null;
                    }
                    return mGson.fromJson(jsonStr, Site.class);
                });
    }

    private Observable<String> fullUrlObservable(String relUrl) {
        return Observable.just(relUrl)
                .map(relativeUrl -> mBaseUrl + relativeUrl);
    }

    private <T> Observable<T> fetchDataPage(String relUrl, Class<T> clz) {
        return fullUrlObservable(relUrl)
                .map(url -> {
                    String jsonStr = mCache.getString(url);
                    if (jsonStr == null) {
                        return null;
                    }
                    return mGson.fromJson(jsonStr, clz);
                });
    }
}
