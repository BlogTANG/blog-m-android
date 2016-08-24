package im.r_c.android.blogm.data.source;

import im.r_c.android.blogm.data.model.Archive;
import im.r_c.android.blogm.data.model.CustomPage;
import im.r_c.android.blogm.data.model.Post;
import im.r_c.android.blogm.data.model.PostList;
import im.r_c.android.blogm.data.model.Site;
import rx.Observable;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class Repository implements DataSource {

    private LocalDataSource mLocalDataSource;
    private RemoteDataSource mRemoteDataSource;

    public Repository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<PostList> getPostList(String relUrl) {
        return mLocalDataSource.getPostList(relUrl)
                .concatWith(mRemoteDataSource.getPostList(relUrl));
    }

    @Override
    public Observable<Post> getPost(String relUrl) {
        return mLocalDataSource.getPost(relUrl)
                .concatWith(mRemoteDataSource.getPost(relUrl));
    }

    @Override
    public Observable<CustomPage> getCustomPage(String relUrl) {
        return mLocalDataSource.getCustomPage(relUrl)
                .concatWith(mRemoteDataSource.getCustomPage(relUrl));
    }

    @Override
    public Observable<Archive> getArchive(String relUrl) {
        return mLocalDataSource.getArchive(relUrl)
                .concatWith(mRemoteDataSource.getArchive(relUrl));
    }

    @Override
    public Observable<Site> getSite() {
        return mLocalDataSource.getSite()
                .concatWith(mRemoteDataSource.getSite());
    }
}
