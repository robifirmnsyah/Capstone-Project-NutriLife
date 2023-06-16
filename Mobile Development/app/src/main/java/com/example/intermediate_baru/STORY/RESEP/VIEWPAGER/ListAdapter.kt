package com.example.intermediate_baru.STORY.RESEP.VIEWPAGER

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.intermediate_baru.Model.RecommendedRecipesItem
import com.example.intermediate_baru.R

class ListAdapter(val dataitem: List<RecommendedRecipesItem>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resep, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.namefood.text = dataitem?.get(position)?.name

        holder.itemView.setOnClickListener {
            val name = dataitem?.get(position)?.name
            Toast.makeText(holder.itemView.context, "$name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        if(dataitem !=null){
            return dataitem.size
        }
        return 0
    }


    class MyViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.image)
        val namefood = view.findViewById<TextView>(R.id.namefood)

    }
}