package com.example.kotlindemoapp.di.module

import com.example.kotlindemoapp.adapter.PhotoAdapter
import dagger.Module
import dagger.Provides

@Module
class WorkspacePhotoModule {

    @Provides
    fun providePhotoListAdapter() = PhotoAdapter()
}