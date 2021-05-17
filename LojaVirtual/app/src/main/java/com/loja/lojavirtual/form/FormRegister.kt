package com.loja.lojavirtual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.loja.lojavirtual.R
import com.loja.lojavirtual.databinding.ActivityFormRegisterBinding

class FormRegister : AppCompatActivity() {

    private lateinit var binding: ActivityFormRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {

            Snackbar.make(binding.layoutRegister, "Coloque o seu email e sua senha!", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.BLACK)
                .show()

        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    // Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                    Snackbar.make(binding.layoutRegister, "Cadastro realizado com sucesso", Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.BLACK).setAction("Entrar", View.OnClickListener {
                            changeScreen()
                        })
                        .show()
                }

            }.addOnFailureListener {

                var error = it

                when {
                    error is FirebaseAuthWeakPasswordException -> Snackbar.make(binding.layoutRegister, "Digite uma senha com no mínimo 6 caracteres", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseAuthUserCollisionException -> Snackbar.make(binding.layoutRegister, "Está conta já foi cadastrada", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseNetworkException -> Snackbar.make(binding.layoutRegister, "Sem conexão com á internet", Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(binding.layoutRegister, "Erro ao cadastrar usuário", Snackbar.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun changeScreen() {
        startActivity(Intent(this, FormLogin::class.java))
    }
}