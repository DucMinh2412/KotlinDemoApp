package com.example.kotlindemoapp.di

import com.example.kotlindemoapp.model.Photo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("photos")
    fun getListPhotos(): Observable<List<Photo>>

    @POST("photos")
    fun addItemListPhoto(@Body photo: Photo):Observable<Photo>
}