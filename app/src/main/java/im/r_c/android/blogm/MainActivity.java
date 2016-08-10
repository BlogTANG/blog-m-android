package im.r_c.android.blogm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import im.r_c.android.blogm.data.source.RemoteDataSource;
import im.r_c.android.fusioncache.FusionCache;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    FusionCache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getNetComponent().inject(this);

        RemoteDataSource dataSource = new RemoteDataSource("https://stdrc.cc/api");
        dataSource.getCustomPage("/cv/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(site -> Log.d("Main", "" + mCache.getString("https://stdrc.cc/api/cv/")));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCache.saveMemCacheToDisk();
    }
}
