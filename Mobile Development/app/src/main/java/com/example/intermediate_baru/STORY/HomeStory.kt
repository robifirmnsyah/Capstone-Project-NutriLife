package com.example.intermediate_baru.STORY

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intermediate_baru.databinding.ActivityHomeStoryBinding

class HomeStory : AppCompatActivity() {
    private lateinit var binding: ActivityHomeStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}