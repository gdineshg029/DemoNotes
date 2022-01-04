package com.java.demonotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoNotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}