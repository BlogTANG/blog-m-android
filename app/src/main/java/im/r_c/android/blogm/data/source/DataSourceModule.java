package im.r_c.android.blogm.data.source;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * BlogM
 * Created by richard on 8/11/16.
 */

@Module
public class DataSourceModule {

    private String mBaseUrl;

    public DataSourceModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    LocalDataSource providesLocalDataSource() {
        return new LocalDataSource(mBaseUrl);
    }

    @Provides
    @Singleton
    RemoteDataSource providesRemoteDataSource() {
        return new RemoteDataSource(mBaseUrl);
    }

    @Provides
    @Singleton
    Repository providesRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new Repository(localDataSource, remoteDataSource);
    }
}
