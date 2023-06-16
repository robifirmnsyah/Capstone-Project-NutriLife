package com.example.intermediate_baru.STORY.RESEP.MAIN

import android.content.Intent
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
        val INTENT = "INTENT"
    }

    private lateinit var viewModel: ResepViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resep, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       datalist()
        val layoutManager = LinearLayoutManager(context)
        rv_list = view.findViewById(R.id.recycler_list)
        rv_list.layoutManager = layoutManager
        rv_list.setHasFixedSize(true)
        rv_list.adapter = adapterresep(listresep){
            val intent = Intent(context,Detail_resep::class.java)
            intent.putExtra(INTENT, it)
            startActivity(intent)
        }
    }


    private fun datalist(){

        listresep = arrayListOf<resepitem>()


        img = arrayOf(
            R.drawable.bibimbap,
            R.drawable.nasi_goreng,
            R.drawable.jus_apel,
            R.drawable.pangsit_apel,
            R.drawable.roti_apel
        )

        title = arrayOf(
            getString(R.string.bibimbap),
            getString(R.string.nasi_goreng),
            getString(R.string.jus_apel),
            getString(R.string.pangsit_apel),
            getString(R.string.roti_apel),
        )

        for (i in img.indices){
            val list = resepitem(img [i], title[i])
            listresep.add(list)
        }
    }

}