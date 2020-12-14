package com.example.kotlindemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.kotlindemoapp.adapter.PhotoAdapter
import com.example.kotlindemoapp.databinding.ActivityMainBinding
import com.example.kotlindemoapp.viewmodel.PhotoViewModel
import com.example.kotlindemoapp.viewmodel.PhotoViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: PhotoAdapter

    @Inject
    lateinit var factory: PhotoViewModelFactory

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PhotoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(PhotoViewModel::class.java)

        // set adapter
        binding.rvListPhoto.adapter = adapter

        // call viewModel
        viewModel.callApiGetListPhoto()
        viewModel.getListPhoto().observe(this, Observer {
            if (it != null) {
                adapter.addItems(it)
            }
        })
    }
}