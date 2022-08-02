package com.sampleandroid.app

import android.app.Application
import android.content.Context
import com.sampleandroid.di.module.helperModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


@ExperimentalCoroutinesApi
class BaseApplication : Application() {
    lateinit var mContext: Context

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    helperModule
                )
            )
        }
    }
}
