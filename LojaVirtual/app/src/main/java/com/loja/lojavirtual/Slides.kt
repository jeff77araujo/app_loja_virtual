package com.loja.lojavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide
import com.loja.lojavirtual.databinding.ActivitySlidesBinding
import com.loja.lojavirtual.form.FormLogin

class Slides : IntroActivity() {

    private lateinit var binding: ActivitySlidesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlidesBinding.inflate(layoutInflater)
        //contentView = binding.root

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(

            SimpleSlide.Builder()
                .background(R.color.purple)
                .image(R.drawable.drawer)
                .backgroundDark(R.color.white)
                .title("Loja Online de Calçados")
                .description("Entre e confira as promoções que preparamos para você!")
                .build()
        )

        addSlide(
            SimpleSlide.Builder()
                .background(R.color.av)
                .backgroundDark(R.color.white)
                .title("Crie uma conta grátis")
                .canGoBackward(false)
                .description("Cadastre-se agora mesmo! E venha conhecer os nossos produtos.")
                .build()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        startActivity(Intent(this, FormLogin::class.java))
        finish()
    }
}