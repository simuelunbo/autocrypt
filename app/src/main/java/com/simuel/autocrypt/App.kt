package com.simuel.autocrypt

import android.app.Application
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
           NaverMapSdk.NaverCloudPlatformClient("boc3f18onr")
    }
}