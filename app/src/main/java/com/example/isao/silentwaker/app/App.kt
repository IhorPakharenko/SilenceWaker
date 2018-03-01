package com.example.isao.silentwaker.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Created by Isao on 01.03.2018.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //init JSR-310 backport
        AndroidThreeTen.init(this)
    }
}
