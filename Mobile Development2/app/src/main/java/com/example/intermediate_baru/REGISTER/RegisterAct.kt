package com.example.intermediate_baru.REGISTER

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.intermediate_baru.LOGIN.LoginAct
import com.example.intermediate_baru.databinding.ActivityRegisterBinding

class RegisterAct : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewmodel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding.ButtonRegister.setOnClickListener {
            val name = binding.edName.text?.trim().toString()
            val email = binding.edEmail.text?.trim().toString()
            val password = binding.edPassword.text?.trim().toString()

            if(name.isEmpty()){
                binding.edName.error = "harap isi nama dengan benar"
                return@setOnClickListener
            }

            if(email.isEmpty()){
                binding.edEmail.error = "harap isi email dengan benar"
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.edPassword.error = "harap isi password"
            } else if(password.length < 8) {
                binding.edPassword.error = " password tidak boleh kurang dari 8"
                return@setOnClickListener
            }
            observerRegis()
        }

        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginAct::class.java))
        }
    }
    private fun observerRegis(){
        val name = binding.edName.text?.trim().toString()
        val email = binding.edEmail.text?.trim().toString()
        val password = binding.edPassword.text?.trim().toString()
        viewmodel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewmodel.register(name,email,password)
        viewmodel.statusregister.observe(this){
            if (it!=null){
                Toast.makeText(this, "berhasil register", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginAct::class.java))
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }

        }
    }
}