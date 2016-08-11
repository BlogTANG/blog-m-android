package im.r_c.android.blogm;

import javax.inject.Singleton;

import dagger.Component;
import im.r_c.android.blogm.data.source.LocalDataSource;
import im.r_c.android.blogm.data.source.RemoteDataSource;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(RemoteDataSource remoteDataSource);
    void inject(LocalDataSource localDataSource);
    void inject(MainActivity mainActivity);
}
