package com.example.intermediate_baru.STORY.RESEP.MAIN

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.RESEP.VIEWPAGER.ListAdapter
import com.google.android.material.imageview.ShapeableImageView

class adapterresep(private var reseplist: ArrayList<resepitem>): RecyclerView.Adapter<adapterresep.MyViewHolder>() {

    class MyViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val image: ShapeableImageView = item.findViewById(R.id.image)
        val title: TextView = item.findViewById(R.id.namefood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterresep.MyViewHolder {
       val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_resep, parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: adapterresep.MyViewHolder, position: Int) {
        val currentitem = reseplist[position]
        holder.image.setImageResource(currentitem.image)
        holder.title.text = currentitem.title
    }

    override fun getItemCount(): Int {
        return reseplist.size
    }
}