package im.r_c.android.blogm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import im.r_c.android.blogm.data.Repository;
import im.r_c.android.fusioncache.FusionCache;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository();
        repository.getPostList("/page/2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(page -> Log.d("Main", "" + page));
    }

    @Inject
    FusionCache mCache;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCache.saveMemCacheToDisk();
    }
}
