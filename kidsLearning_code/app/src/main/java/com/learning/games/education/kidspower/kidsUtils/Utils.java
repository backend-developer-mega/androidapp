package com.learning.games.education.kidspower.kidsUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

public class Utils {
    static SharedPreferences sharedPreferences;

    public Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPref", 0);
    }

    public static void setPref(String Key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key, value);
        editor.apply();
    }

    public static Boolean getPref(String Key, boolean value) {
        return Boolean.valueOf(sharedPreferences.getBoolean(Key, value));
    }

    public static void setPref(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getPref(Context context, String key, String value) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, value);
    }

    public static void setPref(Context context, String key, Integer value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value.intValue()).apply();
    }

    public static Integer getPref(Context context, String key, Integer value) {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getInt(key, value.intValue()));
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
