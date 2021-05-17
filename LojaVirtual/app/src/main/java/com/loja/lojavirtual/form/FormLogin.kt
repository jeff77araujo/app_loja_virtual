package com.loja.lojavirtual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.loja.lojavirtual.PrincipalScreen
import com.loja.lojavirtual.R
import com.loja.lojavirtual.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verifyUserLogin()

        supportActionBar!!.hide()

        binding.textForRegister.setOnClickListener {
            startActivity(Intent(this, FormRegister::class.java))
        }

        binding.btnEnter.setOnClickListener {
            authentication()
        }

    }

    private fun authentication() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(binding.layoutLogin, "Digite email e senha!", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.BLACK).show()
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    binding.frameL.visibility = View.VISIBLE
                    Handler().postDelayed({ changePrincipal() }, 2000)
                }

            }.addOnFailureListener {

                var error = it

                when {
                    error is FirebaseAuthInvalidCredentialsException -> Snackbar.make(binding.layoutLogin, "E-mail ou Senha estão incorretos", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseNetworkException -> Snackbar.make(binding.layoutLogin, "Sem conexão com á internet", Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(binding.layoutLogin, "Erro ao logar usuário", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyUserLogin() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            changePrincipal()
        }
    }

    private fun changePrincipal() {
        startActivity(Intent(this, PrincipalScreen::class.java))
        finish()
    }
}