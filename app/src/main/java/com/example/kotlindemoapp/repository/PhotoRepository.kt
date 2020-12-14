package com.example.kotlindemoapp.repository
import com.example.kotlindemoapp.di.ApiService
import com.example.kotlindemoapp.model.Photo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Singleton
class PhotoRepository(private val apiService: ApiService) {

    fun getListPhotos(): Observable<List<Photo>> {
        return apiService.getListPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}