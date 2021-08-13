package com.example.testingninik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testingninik.R
import com.example.testingninik.model.Ebook


class AdapterEbook(var data: ArrayList<Ebook>) : RecyclerView.Adapter<AdapterEbook.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val imgEbook = view.findViewById<ImageView>(R.id.img_ebook)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ebook, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.imgEbook.setImageResource(data[position].gambar)

    }
}