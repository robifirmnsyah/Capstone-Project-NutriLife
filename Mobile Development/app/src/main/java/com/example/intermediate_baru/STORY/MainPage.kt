package com.example.intermediate_baru.STORY

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.intermediate_baru.STORY.LOGOUT.Logout
import com.example.intermediate_baru.R
import com.example.intermediate_baru.STORY.CALCULATOR.calculator
import com.example.intermediate_baru.STORY.CAMERA.MAIN_CAMERA.MainCamea
import com.example.intermediate_baru.STORY.HOME.Home
import com.example.intermediate_baru.STORY.RESEP.MAIN.Resep
import com.example.intermediate_baru.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {

    private lateinit var binding:ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.background = null

        val homefrag = Home()
        val camerafrag = MainCamea()
        val calculatorfrag = calculator()
        val resepfrag = Resep()
        val profilefrag = com.example.intermediate_baru.STORY.PROFILE.Profile()

        SetFragment(homefrag)

        binding.bottomNav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.nav_home -> SetFragment(homefrag)
                R.id.nav_calculator -> SetFragment(calculatorfrag)
                R.id.nav_resep -> SetFragment(resepfrag)
                R.id.nav_profile -> SetFragment(profilefrag)

            }
            true
        }

       binding.camera.setOnClickListener {
           startActivity(Intent(this, MainCamea::class.java))
       }
    }

    private fun SetFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                startActivity(Intent(this, Logout::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}