package com.example.ecomercesystem.favourite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.product_detail.ProductActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.cart.CartActivity
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.android.synthetic.main.favourite_fragment.*
import kotlinx.android.synthetic.main.home_screen_full_fragment.*

class FavouriteFragment : Fragment(R.layout.favourite_fragment), ItemClickInterfaceFavourite {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        itemViewModel = (activity as MainActivity).itemViewModel





        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemFavouriteAdapter(requireContext(), this)
        itemViewModel.favorItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)
            }
        })

        rcv_favourite?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }

        btn_cart_favourite.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }


        //chuyen tab categories
        btn_menu_toolbar_favorite?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun OnItemClick(item: ItemFavor) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }

    override fun OnDeleteBtnClick(item: ItemFavor) {
        itemViewModel.deleteFavorItem(item)
        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
    }

}