package com.example.intermediate_baru.STORY.LOGOUT

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intermediate_baru.LOGIN.LoginAct
import com.example.intermediate_baru.LOGIN.SharePreference
import com.example.intermediate_baru.databinding.ActivityLogoutBinding

class Logout : AppCompatActivity() {
    private lateinit var binding: ActivityLogoutBinding
    private lateinit var prefer: SharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLogoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        prefer = SharePreference(this)

        binding.ButtonLogout.setOnClickListener {
            prefer.clearUser()
            startActivity(Intent(this, LoginAct::class.java))
        }
    }

}