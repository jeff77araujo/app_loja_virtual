package com.loja.lojavirtual.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRegister(
    val uid: String = "",
    val name: String = "",
    val price: String = "",
    val url: String = ""
): Parcelable