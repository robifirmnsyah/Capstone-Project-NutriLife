package com.example.intermediate_baru.STORY.CALCULATOR

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import com.example.intermediate_baru.R
import com.example.intermediate_baru.databinding.FragmentCalculatorBinding
import okio.IOException
import java.io.BufferedReader
import java.io.InputStreamReader

class calculator : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private lateinit var data: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)

        val searchInput = view?.findViewById<SearchView>(R.id.searchBar)
        val searchResults = view?.findViewById<ListView>(R.id.searchresult)
        val gramInput = view?.findViewById<TextView>(R.id.inputgram)
        val resultTextView = view?.findViewById<TextView>(R.id.resultTextView)

        val selectgram = binding.inputgram.text.toString().toFloat()
        val selectbahan = binding.kaloribahan.text.toString()

        data = loadDataFromFile()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)
        searchResults?.adapter = adapter

        // Set click listener for calculate button
        binding.buttonresult.setOnClickListener {
            if (selectbahan.isBlank() || selectgram == null || selectgram <= 0) {
                binding.resultTextView.text = "Invalid input"
            }
            val bahanIndex = data.indexOf(selectbahan)
            if (bahanIndex == -1) {
                binding.resultTextView.text = "Bahan not found"
            }
        }
    }
    private fun loadDataFromFile(): List<String> {
        val data = mutableListOf<String>()

        try {
            val inputStream = resources.assets.open("calorie_infos_final.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                // Menambahkan setiap baris data ke list
                data.add(line!!)
            }

            reader.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return data
    }
}