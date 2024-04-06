package com.example.kotlinbestpractice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** Created By Ranga
on 04-04-2024
 **/
@HiltAndroidApp
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}