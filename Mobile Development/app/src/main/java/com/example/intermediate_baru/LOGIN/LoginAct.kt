package com.example.intermediate_baru.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.intermediate_baru.Model.User
import com.example.intermediate_baru.REGISTER.RegisterAct
import com.example.intermediate_baru.REGISTER.RegisterViewModel
import com.example.intermediate_baru.STORY.HomeStory
import com.example.intermediate_baru.databinding.ActivityLoginBinding

class LoginAct : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewmodel: LoginViewModel
    private lateinit var prefer: SharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefer = SharePreference(this)

        if (prefer.berhasil_masuk) {
            startActivity(Intent(this, HomeStory::class.java))
            finish()
            return
        }

        binding.ButtonLogin.setOnClickListener {

            val email = binding.edEmail.text?.trim().toString()
            val password = binding.edPassword.text?.trim().toString()

            if (email.isEmpty()) {
                binding.edEmail.error = "harap isi email dengan benar"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.edPassword.error = "harap isi password"
            } else if (password.length < 8) {
                binding.edPassword.error = " password tidak boleh kurang dari 8"
                return@setOnClickListener
            }

            observerLogin()
        }

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterAct::class.java))
        }


    }

    private fun observerLogin() {
        val email = binding.edEmail.text?.trim().toString()
        val password = binding.edPassword.text?.trim().toString()
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewmodel.initPreferences(this)
        viewmodel.login(email, password)
        viewmodel.statuslogin.observe(this) {
            if (it != null) {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
                if (prefer.berhasil_masuk) {
                    startActivity(Intent(this, HomeStory::class.java))
                } else {
                    Toast.makeText(this, "anda belum log in", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        }
    }

}