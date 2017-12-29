package com.example.oscar.finger;

import android.content.Context;
import android.util.Log;

import org.acra.ACRA;

/**
 * Created by Oscar on 28/12/2017.
 */

public class ApplicationBase extends android.app.Application {
    public static ApplicationBase instance;
    private static Context context;

    private static final String TAG = "ApplicationBase";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            System.setProperty("jna.nounpack", "true");
            System.setProperty("java.io.tmpdir", getCacheDir().getAbsolutePath());
            ACRA.init(this);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }


    public static ApplicationBase getIntance() {
        if(instance==null){
            instance=new ApplicationBase();
            instance.initApplication();
        }
        return instance;
    }

    private void initApplication() {
        instance = this;
        context = getApplicationContext();
    }


}
