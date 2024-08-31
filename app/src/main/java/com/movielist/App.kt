package com.movielist

import android.app.Application
import android.content.res.Configuration
import com.movielist.ui.MainActivity
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //Make app language is english
        val local = Locale.ENGLISH
        MainActivity.currentLocale = local
        Locale.setDefault(local)
        Configuration().setLocale(local)
    }
}