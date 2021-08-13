package com.example.testingninik.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.testingninik.adapter.AdapterImageSlider.SliderHolder
import com.example.testingninik.databinding.ItemImageSliderBinding
import com.example.testingninik.model.ModelImageSlider
import com.smarteist.autoimageslider.SliderViewAdapter


/**
 * Created by Dhimas Panji Sastra on
 * Copyright (c)  . All rights reserved.
 * Last modified $file.lastModified
 * Made With ❤ for U
 */

class AdapterImageSlider(context: Context, itemimage: ArrayList<ModelImageSlider>) :
    SliderViewAdapter<SliderHolder>() {
    var itemimage: ArrayList<ModelImageSlider> = itemimage
    var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup): SliderHolder {
        return SliderHolder(
            ItemImageSliderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: SliderHolder,
        position: Int
    ) {
        val item: ArrayList<ModelImageSlider> = itemimage
        val imageSlider: ModelImageSlider = itemimage[position]
        Glide.with(context)
            .load(imageSlider.url)
            .fitCenter()
            .dontAnimate()
            .into(viewHolder.binding.itemSlider)
    }

    override fun getCount(): Int {
        return itemimage.size
    }

    inner class SliderHolder(itemView: ItemImageSliderBinding) :
        ViewHolder(itemView.root) {
        var binding: ItemImageSliderBinding = itemView

    }

}