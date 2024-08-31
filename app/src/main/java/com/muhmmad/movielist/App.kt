package com.muhmmad.movielist

import android.app.Application
import android.content.res.Configuration
import com.muhmmad.movielist.presentation.MainActivity
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val local = Locale.ENGLISH
        MainActivity.currentLocale = local
        Locale.setDefault(local)
        Configuration().setLocale(local)
    }
}