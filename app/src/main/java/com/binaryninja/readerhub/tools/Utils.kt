package com.binaryninja.readerhub.tools

import android.content.Context
import java.util.*

object Utils {
    fun loadLanguage(c: Context, lang: String?) {
        var lang = lang
        val sharedPref = c.getSharedPreferences(Constant.SHARED_PREFNAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        if (lang == null) lang = sharedPref.getString(
            "language",
            Locale.getDefault().language
        ) else editor.putString("language", lang).apply()
        val configuration = c.resources.configuration
        val locale = Locale(lang)
        Locale.setDefault(locale)
        configuration.locale = locale
        c.resources.updateConfiguration(configuration, c.resources.displayMetrics)
    }

    fun ListToString(array: List<String?>?): String {
        val data = StringBuilder()
        if (array != null) {
            for (i in array.indices) {
                if (i != array.size - 1) data.append(array[i]).append(",") else {
                    data.append(array[i])
                }
            }
        }
        return data.toString()
    }
}