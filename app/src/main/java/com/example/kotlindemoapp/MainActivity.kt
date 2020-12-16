package com.example.kotlindemoapp

import android.content.Intent
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

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
        setDataBindingAndViewModel()

        // call viewModel
        viewModel.callApiGetListPhoto()
        viewModel.getListPhoto().observe(this, Observer {
            if (it != null) {
                adapter.addItems(it)
            }
        })

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setDI() {
        AndroidInjection.inject(this)
    }

    private fun setDataBindingAndViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PhotoViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvListPhoto.adapter = adapter
    }
}