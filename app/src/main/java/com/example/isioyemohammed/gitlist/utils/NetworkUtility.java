package com.example.isioyemohammed.gitlist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by isioyemohammed on 25/03/2018.
 * Gitlist
 */

public class NetworkUtility {
    /**
     * Context variable.
     */
    private final Context context;

    /**
     * Network Context parameter.
     * @param context - Context
     */
    public NetworkUtility(Context context) {
        this.context = context;
    }

    /**
     * isConnected method.
     * @return boolean
     */
    public boolean isConnected() {
        if (context != null) {
            ConnectivityManager manager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert manager != null;
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
