package com.binaryninja.readerhub;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static void loadLanguage(Context c,String lang){
        SharedPreferences sharedPref = c.getSharedPreferences("",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (lang == null)
            lang = sharedPref.getString("language", Locale.getDefault().getLanguage());
        else
            editor.putString("language", lang).apply();
        Configuration configuration = c.getResources().getConfiguration();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        configuration.locale = locale;
        c.getResources().updateConfiguration(configuration, c.getResources().getDisplayMetrics());

    }
    public static String ListToString(List<String> array) {
        StringBuilder data = new StringBuilder();
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                if (i != array.size() - 1)
                    data.append(array.get(i)).append(",");
                else {
                    data.append(array.get(i));
                }

            }
        }
        return data.toString();
    }
}
