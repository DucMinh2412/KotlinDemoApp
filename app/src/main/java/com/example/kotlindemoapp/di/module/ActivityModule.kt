package com.example.kotlindemoapp.di.module

import com.example.kotlindemoapp.AddListActivity
import com.example.kotlindemoapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// khai báo các activity được inject module
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [WorkspacePhotoModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [WorkspacePhotoModule::class])
    abstract fun contributeAddListActivity(): AddListActivity
}