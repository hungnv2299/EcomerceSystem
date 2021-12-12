package com.example.ecomercesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ecomercesystem.data.ItemVIewModel
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.iv_product_page
import kotlinx.android.synthetic.main.activity_product.tv_name_product_page
import kotlinx.android.synthetic.main.activity_product.tv_price_product_page
import kotlinx.android.synthetic.main.activity_product.tv_rating_product_page
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    lateinit var itemViewModel: ItemVIewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        val intent = intent
        var a = intent.getStringExtra("name")
        itemViewModel.getItemByName(intent.getStringExtra("name")!!)

        Glide.with(this).load(itemViewModel.getItemByName(a!!).imgsrc).into(iv_product_page_detail)
        tv_name_product_page_detail.text = itemViewModel.getItemByName(a!!).name
        tv_price_product_page_detail.text = "Rs. "+itemViewModel.getItemByName(a!!).price.toString()
        tv_rating_product_page_detail.text = itemViewModel.getItemByName(a!!).rating.toString()+"/5"
        tv_detail_product_detail_page.text = itemViewModel.getItemByName(a!!).detail
    }
}