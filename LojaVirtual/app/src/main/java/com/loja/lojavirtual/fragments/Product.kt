package com.loja.lojavirtual.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.loja.lojavirtual.R
import com.loja.lojavirtual.databinding.FragmentProductsBinding
import com.loja.lojavirtual.model.DataRegister
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class Product : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    private lateinit var adapterGroup: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_products, binding.root, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapterGroup = GroupAdapter()
        binding.recycleProduct.adapter = adapterGroup

        searchProducts()
    }

    private inner class ProductsItem(internal val adProducts: DataRegister) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.list_product
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.nameProduct.text = adProducts.name
            viewHolder.item.priceProduct.text = adProducts.price
            Picasso.get().load(adProducts.uid).into(viewHolder.item.photoProduct)
        }
    }


    private fun searchProducts() {
        FirebaseFirestore.getInstance().collection("Products").addSnapshotListener { snapshot, exception ->
                exception?.let {
                    return@addSnapshotListener
                }

                snapshot?.let {
                    for (doc in snapshot) {
                        val products = doc.toObject(DataRegister::class.java)
                        adapterGroup.add(ProductsItem(products))
                    }
                }
            }
    }

}