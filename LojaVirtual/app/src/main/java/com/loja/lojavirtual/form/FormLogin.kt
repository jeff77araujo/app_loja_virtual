package com.loja.lojavirtual.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loja.lojavirtual.R
import com.loja.lojavirtual.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.textForRegister.setOnClickListener {
            startActivity(Intent(this, FormRegister::class.java))
        }
    }
}