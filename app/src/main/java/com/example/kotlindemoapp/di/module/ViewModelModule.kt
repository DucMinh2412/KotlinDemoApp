package com.example.kotlindemoapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemoapp.di.ViewModelKey
import com.example.kotlindemoapp.viewmodel.PhotoViewModel
import com.example.kotlindemoapp.viewmodel.PhotoViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * định nghĩa các viewModel
 * Activity hoặc Fragment sẽ sử dụng ViewModelFactory để inject ViewModel tương ứng với nó.
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: PhotoViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    abstract fun photoViewModel(viewModel: PhotoViewModel): ViewModel
}

