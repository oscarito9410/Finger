package com.example.oscar.finger.Utils.Licensing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Oscar on 29/12/2017.
 */

public class Utils {
    public static  boolean canWritteSettings(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(act)) {
                return true;
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + act.getPackageName()));
                act.startActivityForResult(intent, 200);
            }
        } else {
            return true;
        }
        return false;
    }
}
