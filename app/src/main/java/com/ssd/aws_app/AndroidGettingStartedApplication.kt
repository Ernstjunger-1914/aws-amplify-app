package com.ssd.aws_app

import android.app.Application

class AndroidGettingStartedApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Backend.init(applicationContext)
    }

}