package im.r_c.android.blogm.util;

import javax.inject.Singleton;

import dagger.Component;
import im.r_c.android.blogm.AppModule;
import im.r_c.android.blogm.view.MainActivity;
import im.r_c.android.blogm.data.source.LocalDataSource;
import im.r_c.android.blogm.data.source.RemoteDataSource;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

@Singleton
@Component(modules = {AppModule.class, ToolModule.class})
public interface ToolComponent {
    void inject(RemoteDataSource remoteDataSource);
    void inject(LocalDataSource localDataSource);
    void inject(MainActivity mainActivity);
}
