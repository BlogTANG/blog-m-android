package im.r_c.android.blogm.data.source;

import javax.inject.Singleton;

import dagger.Component;
import im.r_c.android.blogm.view.PostListFragment;

/**
 * BlogM
 * Created by richard on 8/11/16.
 */

@Singleton
@Component(modules = DataSourceModule.class)
public interface DataSourceComponent {
    void inject(PostListFragment postListFragment);
}
