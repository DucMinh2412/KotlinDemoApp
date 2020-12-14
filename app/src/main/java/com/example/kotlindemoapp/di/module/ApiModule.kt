package com.example.kotlindemoapp.di.module
import com.example.kotlindemoapp.di.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 *  @Module có trách nhiệm cung cấp các object có thể được inject (được sử dụng trong view)
 *  Các class như vậy có thể định nghĩa các phương thức với anotated @Provides.
 *  Các đối tượng trả về từ các phương thức này có sẵn dependency để inject.
 *
 *  Bạn có thể sử dụng @Inject để định nghĩa dependency.Nếu bạn đặt @Inject với constructor,Dagger 2 có thể
    sử dụng 1 instance này để hoàn thành dependencies.Điều này được thực hiện để tránh việc define nhiều phương thức
    @Provider cho các đối tượng này.
 */

/* cung cấp các phương thức trả về những cài đặt đối với việc gọi Api có sẵn */
@Module
class ApiModule {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
       return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiService {
       return retrofit.create(ApiService::class.java)
    }

}