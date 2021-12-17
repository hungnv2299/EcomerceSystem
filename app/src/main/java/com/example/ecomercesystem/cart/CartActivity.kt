package com.example.ecomercesystem.cart

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.R
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.product_detail.ProductActivity
import kotlinx.android.synthetic.main.checkout_page.*

class CartActivity:AppCompatActivity(), ItemClickInterfaceCart{
    lateinit var itemViewModel:ItemVIewModel
    lateinit var manager:RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_page)
        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemCartAdapter(this, this)
        itemViewModel.cartItems.observe(this, { list ->
            list?.let {
                itemAdapter.updateList(list)
                sub_total.text = "Rs. "+itemViewModel.getSubTotal().toString()
                number_items.text = "("+itemViewModel.getAmount().toString()+" items)"
                total_price.text = "Rs. "+itemViewModel.getSubTotal().toString()
            }
        })

        rcv_cart.apply {
            adapter = itemAdapter
            layoutManager = manager
        }
        btn_back_cart.setOnClickListener {
            onBackPressed()
        }

        button_checkout.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Vui lòng thanh toán Rs." + itemViewModel.getSubTotal().toString())
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
            val alert = dialogBuilder.create()
            alert.setTitle("Cảm ơn bạn đã mua hàng")
            alert.show()
            itemViewModel.deleteCart()

        }
    }

    override fun OnItemClick(item: ItemCart) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }

    override fun OnDeleteBtnClick(item: ItemCart) {
        itemViewModel.deleteFromCart(item)
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun OnPlusClick(item: ItemCart) {
        itemViewModel.itemCartPlus(item.name)
    }

    override fun OnMinusClick(item: ItemCart) {
        itemViewModel.itemCartMinus(item)
    }
}