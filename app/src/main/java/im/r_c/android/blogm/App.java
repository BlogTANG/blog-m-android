package im.r_c.android.blogm;

import android.app.Application;

import im.r_c.android.blogm.data.source.DaggerDataSourceComponent;
import im.r_c.android.blogm.data.source.DataSourceComponent;
import im.r_c.android.blogm.data.source.DataSourceModule;
import im.r_c.android.blogm.util.DaggerToolComponent;
import im.r_c.android.blogm.util.ToolComponent;
import im.r_c.android.blogm.util.ToolModule;

/**
 * BlogM
 * Created by richard on 8/10/16.
 */

public class App extends Application {

    private static ToolComponent sToolComponent;
    private static DataSourceComponent sDataSourceComponent;

    public static ToolComponent getToolComponent() {
        return sToolComponent;
    }

    public static DataSourceComponent getDataSourceComponent() {
        return sDataSourceComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sToolComponent = DaggerToolComponent.builder()
                .appModule(new AppModule(this))
                .toolModule(new ToolModule())
                .build();

        sDataSourceComponent = DaggerDataSourceComponent.builder()
                .dataSourceModule(new DataSourceModule(getString(R.string.base_url)))
                .build();
    }
}
