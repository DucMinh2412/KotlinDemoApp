package com.example.kotlindemoapp.di

import com.example.kotlindemoapp.model.Photo
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    fun getListPhotos(): Observable<List<Photo>>
}