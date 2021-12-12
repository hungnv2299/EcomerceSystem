package com.example.ecomercesystem.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.ProductActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.android.synthetic.main.checkout_page.*
import kotlinx.android.synthetic.main.home_screen_fargment.*
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import java.util.ArrayList

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

        rcv_checkout_page?.apply {
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
        Toast.makeText(requireContext(), "+", Toast.LENGTH_SHORT).show()
    }

    override fun OnMinusClick(item: ItemCart) {
        Toast.makeText(requireContext(), "_", Toast.LENGTH_SHORT).show()
    }

}