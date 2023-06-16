package com.example.intermediate_baru.STORY.RESEP.MAIN

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.RESEP.ResepViewModel

class Resep : Fragment() {

    private lateinit var adapter: adapterresep
    private lateinit var rv_list: RecyclerView
    private lateinit var listresep: ArrayList<resepitem>

    lateinit var img: Array<Int>
    lateinit var title: Array<String>

    companion object {
        fun newInstance() = Resep()
    }

    private lateinit var viewModel: ResepViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resep, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        datalist()
        val layoutmanager = LinearLayoutManager(context)
        rv_list = view?.findViewById(R.id.recycler_list)
        rv_list.layoutManager = layoutmanager
        rv_list.setHasFixedSize(true)
        adapter = adapterresep(listresep)

    }

    private fun datalist(){
        img = arrayOf(
            R.drawable.
            R.drawable.
            R.drawable.
            R.drawable.
            R.drawable.
        )

        title = arrayOf(
            getString(R.string),
            getString(R.string),
            getString(R.string),
            getString(R.string),
            getString(R.string),
        )

        for (i in img.indices){
            val list: res
        }
    }

}