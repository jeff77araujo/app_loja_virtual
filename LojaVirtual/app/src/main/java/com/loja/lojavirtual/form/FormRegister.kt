package com.loja.lojavirtual.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loja.lojavirtual.databinding.ActivityFormRegisterBinding

class FormRegister : AppCompatActivity() {

    private lateinit var binding: ActivityFormRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
    }
}