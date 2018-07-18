package com.example.isioyemohammed.gitlist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by isioyemohammed on 25/03/2018.
 * Gitlist
 */

public final class NetworkUtility {
    /**
     * Empty constructor.
     */
    private NetworkUtility() {
        // Needed
    }

    /**
     * isConnected method.
     *
     * @param context - Context
     * @return boolean
     */
    public static boolean isConnected(Context context) {
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
