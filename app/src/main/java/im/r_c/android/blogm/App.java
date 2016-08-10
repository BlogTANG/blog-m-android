package im.r_c.android.blogm;

import android.app.Application;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class App extends Application {

    private static NetComponent sNetComponent;

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }
}
