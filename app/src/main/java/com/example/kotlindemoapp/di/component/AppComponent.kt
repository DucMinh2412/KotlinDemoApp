package com.example.kotlindemoapp.di.component

import android.app.Application
import com.example.kotlindemoapp.di.AppController
import com.example.kotlindemoapp.di.module.ActivityModule
import com.example.kotlindemoapp.di.module.ApiModule
import com.example.kotlindemoapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @Component được sử dụng trong 1 interface. Interface này được sử dụng bởi Dagger2 nhằm generate code,
kết nối giữa module và đối tượng thể hiện sự phụ thuộc (@Inject)
-> chạy xuyên suốt ứng dụng
 */
@Component(
    modules = [(AndroidInjectionModule::class),
        (ApiModule::class),
        (ActivityModule::class),
        (ViewModelModule::class)
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(appController: AppController)
}