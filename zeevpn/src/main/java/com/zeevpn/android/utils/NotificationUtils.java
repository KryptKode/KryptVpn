package com.zeevpn.android.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Cyberman on 5/6/2018.
 */

public class NotificationUtils {

    public static void notifyUser(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
