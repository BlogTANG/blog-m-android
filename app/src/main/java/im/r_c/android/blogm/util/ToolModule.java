package im.r_c.android.blogm.util;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.r_c.android.fusioncache.FusionCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

@Module
public class ToolModule {

    public ToolModule() {
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder()
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
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
                    DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                    Date date = null;
                    try {
                        date = format.parse(json.getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return date;
                })
                .create();
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
