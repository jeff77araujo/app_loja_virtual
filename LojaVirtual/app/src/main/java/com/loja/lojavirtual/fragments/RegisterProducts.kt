package com.loja.lojavirtual.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.loja.lojavirtual.R
import com.loja.lojavirtual.databinding.ActivityRegisterProductsBinding
import com.loja.lojavirtual.model.DataRegister
import java.util.*

class RegisterProducts : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterProductsBinding
    private var selectedUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectedPhoto.setOnClickListener {
            selectedPhoto()
        }

        binding.btnRegisterProduct.setOnClickListener {
            saveDataFirebase()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            selectedUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedUri)
            binding.photoProduct.setImageBitmap(bitmap)
            binding.btnSelectedPhoto.alpha = 0f
        }
    }

    private fun selectedPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    private fun saveDataFirebase() {
        val nameFile = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/image/${nameFile}")

        selectedUri?.let {
            reference.putFile(it).addOnSuccessListener {
                reference.downloadUrl.addOnSuccessListener {

                    val url = it.toString()
                    val name = binding.editName.text.toString()
                    val price = binding.editPrice.text.toString()
                    val uid = FirebaseAuth.getInstance().uid

                    val product = DataRegister(url, name, price)

                    FirebaseFirestore.getInstance().collection("Products")
                        .add(product)
                        .addOnSuccessListener {
                            Snackbar.make(binding.layoutRegisterProduct, "Produto cadastrado com sucesso!", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.BLACK).show()
                        }.addOnFailureListener {

                        }

                }
            }
        }
    }
}
