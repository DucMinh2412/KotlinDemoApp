package com.example.kotlindemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemoapp.di.ApiService
import com.example.kotlindemoapp.model.Photo
import com.example.kotlindemoapp.repository.PhotoRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val mubListPhoto = MutableLiveData<List<Photo>>()
    private val repository = PhotoRepository(apiService)
    private val disposable = CompositeDisposable()


    fun getListPhoto(): MutableLiveData<List<Photo>> = mubListPhoto

    fun callApiGetListPhoto() {
        disposable.add(
            repository.getListPhotos().subscribe(
                // success
                { mubListPhoto.value = it },
                // error
                { print(it.toString()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}