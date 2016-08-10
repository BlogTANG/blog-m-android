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

    @Override
    public Observable<PostList> getPostList(String relUrl) {
        return null;
    }

    @Override
    public Observable<Post> getPost(String relUrl) {
        return null;
    }

    @Override
    public Observable<CustomPage> getCustomPage(String relUrl) {
        return null;
    }

    @Override
    public Observable<Archive> getArchive(String relUrl) {
        return null;
    }

    @Override
    public Observable<Site> getSite() {
        return null;
    }
}
