package com.example.kotlindemoapp.viewmodel

import android.content.res.Resources
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemoapp.R
import com.example.kotlindemoapp.di.ApiService
import com.example.kotlindemoapp.model.Photo
import com.example.kotlindemoapp.repository.PhotoRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    var albumId = MutableLiveData<String>()
    var id = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var url = MutableLiveData<String>()
    var thumbnailUrl = MutableLiveData<String>()
    private val message = MutableLiveData<String>()
    private val mubListPhoto = MutableLiveData<List<Photo>>()
    private val mubPhoto = MutableLiveData<Photo>()
    private val repository = PhotoRepository(apiService)
    private val disposable = CompositeDisposable()


    fun getListPhoto(): MutableLiveData<List<Photo>> = mubListPhoto
    fun getPhoto(): MutableLiveData<Photo> = mubPhoto
    fun getMessage(): MutableLiveData<String> = message

    fun callApiGetListPhoto() {
        disposable.add(
            repository.getListPhotos().subscribe(
                // success
                { mubListPhoto.value = it },
                // error
                { Log.e("error", "error") })
        )
    }

    fun callApiAddItemListPhoto(view: View) {
        when {
            albumId.value.isNullOrEmpty() -> {
                message.value = view.resources.getString(R.string.error_album_id)
            }
            id.value.isNullOrEmpty() -> {
                message.value = view.resources.getString(R.string.error_id)
            }
            title.value.isNullOrEmpty() -> {
                message.value = view.resources.getString(R.string.error_title)
            }
            url.value.isNullOrEmpty() -> {
                message.value = view.resources.getString(R.string.error_url)
            }
            thumbnailUrl.value.isNullOrEmpty() -> {
                message.value = view.resources.getString(R.string.error_thumbnail_url)
            }
            else -> {
                disposable.add(
                    repository.addItemListPhoto(
                        Photo(
                            albumId.value!!.toInt(),
                            id.value!!.toInt(),
                            title.value!!,
                            url.value!!,
                            thumbnailUrl.value!!
                        )
                    ).subscribe(
                        // success
                        { mubPhoto.value = it },
                        // error
                        { Log.e("error", "error") }
                    )

                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}