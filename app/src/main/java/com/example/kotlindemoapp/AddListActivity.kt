package com.example.kotlindemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlindemoapp.adapter.PhotoAdapter
import com.example.kotlindemoapp.databinding.ActivityAddListBinding
import com.example.kotlindemoapp.model.Photo
import com.example.kotlindemoapp.viewmodel.PhotoViewModel
import com.example.kotlindemoapp.viewmodel.PhotoViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class AddListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: PhotoViewModelFactory

    @Inject
    lateinit var adapter: PhotoAdapter

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var binding: ActivityAddListBinding
    private val list = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDI()
        setDataBindingAndViewModel()

        photoViewModel.getMessage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        photoViewModel.getPhoto().observe(this, Observer {
            if (it != null) {
                list.add(it)
                adapter.clearItems()
                adapter.addItems(list)
            }
        })
    }

    private fun setDI() {
        AndroidInjection.inject(this)
    }

    private fun setDataBindingAndViewModel() {
        photoViewModel = ViewModelProviders.of(this, factory).get(PhotoViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_list)
        binding.lifecycleOwner = this
        binding.viewModel = photoViewModel
        binding.rvListPhoto.adapter = adapter
    }
}