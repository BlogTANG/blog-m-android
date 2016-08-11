package im.r_c.android.blogm;

import android.app.Application;

import im.r_c.android.blogm.data.source.DataSourceComponent;
import im.r_c.android.blogm.data.source.DataSourceModule;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class App extends Application {

    private static NetComponent sNetComponent;
    private static DataSourceComponent sDataSourceComponent;

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }

    public static DataSourceComponent getDataSourceComponent() {
        return sDataSourceComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        sDataSourceComponent = DaggerDataSourceComponent.builder()
                .dataSourceModule(new DataSourceModule("https://stdrc.cc/api"))
                .build();
    }
}
