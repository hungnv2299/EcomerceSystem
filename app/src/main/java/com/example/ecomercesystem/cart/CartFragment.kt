package com.example.ecomercesystem.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.product_detail.ProductActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import kotlinx.android.synthetic.main.checkout_page.*

class CartFragment : Fragment(R.layout.checkout_page), ItemClickInterfaceCart {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
//    val cartFragment = CartFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        itemViewModel = (activity as MainActivity).itemViewModel
        cart.setOnClickListener {
            itemViewModel.getCartItems()
        }
        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemCartAdapter(requireContext(), this)
        itemViewModel.cartItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)
                sub_total.text = "Rs. "+itemViewModel.getSubTotal().toString()
                number_items.text = "("+itemViewModel.getAmount().toString()+" items)"
                total_price.text = "Rs. "+itemViewModel.getSubTotal().toString()
            }
        })

        rcv_cart?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }


    }


    override fun OnItemClick(item: ItemCart) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }

    override fun OnDeleteBtnClick(item: ItemCart) {
        itemViewModel.deleteFromCart(item)
        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun OnPlusClick(item: ItemCart) {
        itemViewModel.itemCartPlus(item.name)
    }

    override fun OnMinusClick(item: ItemCart) {

        itemViewModel.itemCartMinus(item)
    }

}