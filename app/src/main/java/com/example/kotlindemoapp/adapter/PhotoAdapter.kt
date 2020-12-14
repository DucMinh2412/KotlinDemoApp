package com.example.kotlindemoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.R
import com.example.kotlindemoapp.databinding.RowPhotoBinding
import com.example.kotlindemoapp.model.Photo

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    private val list: ArrayList<Photo> = ArrayList()

    fun addItems(photoList: List<Photo>) {
        list.addAll(photoList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        list.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_photo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list?.size


    inner class PhotoHolder(private val binding: RowPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.photo = list[position]
            binding.executePendingBindings()
        }
    }
}