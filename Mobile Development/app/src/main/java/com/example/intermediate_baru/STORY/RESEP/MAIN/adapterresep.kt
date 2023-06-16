package com.example.intermediate_baru.STORY.RESEP.MAIN

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.RESEP.VIEWPAGER.ListAdapter
import com.google.android.material.imageview.ShapeableImageView

class adapterresep(private var reseplist: ArrayList<resepitem>, val listener: (resepitem) -> Unit): RecyclerView.Adapter<adapterresep.MyViewHolder>() {

    class MyViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val image: ImageView = item.findViewById(R.id.image)
        val title: TextView = item.findViewById(R.id.namefood)

        fun bindView(list:resepitem, listener:(resepitem) -> Unit){
            image.setImageResource(list.image)
            title.text = list.title
            itemView.setOnClickListener{
                listener(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterresep.MyViewHolder {
       val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_resep, parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: adapterresep.MyViewHolder, position: Int) {
        holder.bindView(reseplist[position], listener)

        val currentitem = reseplist[position]
        holder.image.setImageResource(currentitem.image)
        holder.title.text = currentitem.title
        itemCount
    }

    override fun getItemCount(): Int {
        return reseplist.size
    }

}