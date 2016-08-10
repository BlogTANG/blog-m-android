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

public interface DataSource {

    Observable<PostList> getPostList(String relUrl);

    Observable<Post> getPost(String relUrl);

    Observable<CustomPage> getCustomPage(String relUrl);

    Observable<Archive> getArchive(String relUrl);

    Observable<Site> getSite();
}
