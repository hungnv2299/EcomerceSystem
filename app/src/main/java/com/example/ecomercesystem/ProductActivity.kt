package com.example.ecomercesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    lateinit var itemViewModel: ItemVIewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        val intent = intent
        Toast.makeText(this, "Intent: "+intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()
        var a = intent.getStringExtra("name")
        itemViewModel.getItemByName(intent.getStringExtra("name")!!)
//        Toast.makeText(this, itemViewModel.getItemByName(a!!).name, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, itemViewModel.getItemByName(a!!).imgsrc, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, itemViewModel.getItemByName(a!!).price.toString(), Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, itemViewModel.getItemByName(a!!).rating.toString(), Toast.LENGTH_SHORT).show()

//        var item : Item = itemViewModel.selectedItems.value?.get(0)!!
        Glide.with(this).load(itemViewModel.getItemByName(a!!).imgsrc).into(iv_product_page)
        tv_name_product_page.text = itemViewModel.getItemByName(a!!).name
        tv_price_product_page.text = "Rs. "+itemViewModel.getItemByName(a!!).price.toString()
        tv_rating_product_page.text = itemViewModel.getItemByName(a!!).rating.toString()+"/5"
        iv_product_page.setOnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("name", itemViewModel.getItemByName(a!!).name)
            startActivity(intent)
        }
        btn_add_to_cart_productpage.setOnClickListener {
            itemViewModel.addToCart(ItemCart(itemViewModel.getItemByName(a!!).name, itemViewModel.getItemByName(a!!).imgsrc, itemViewModel.getItemByName(a!!).price, 1))
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show()
        }
    }
}