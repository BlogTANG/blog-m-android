package im.r_c.android.blogm;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.r_c.android.fusioncache.FusionCache;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

@Module
public class NetModule {
    private static final String TAG = "NetModule";

    public NetModule() {
    }

    @Provides
    @Singleton
    Cache providesOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request newRequest = original.newBuilder()
                            .header("Accept", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    FusionCache providesFusionCache(Application application) {
        return new FusionCache(application.getApplicationContext(),
                4 * 1024 * 1024,
                20 * 1024 * 1024,
                true);
    }
}
