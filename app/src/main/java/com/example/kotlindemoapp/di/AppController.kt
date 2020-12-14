package com.example.kotlindemoapp.di

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDexApplication
import com.example.kotlindemoapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// custom một Application để lưu trữ AppComponent và giữ chúng trong suốt quá trình chạy của ứng dụng.
class AppController : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return dispatchingAndroidInjector
    }
}