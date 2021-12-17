package com.example.ecomercesystem.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.databinding.HomeScreenFargmentBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemFavor
import com.example.ecomercesystem.home_full.HomeFullFragment
import com.example.ecomercesystem.search.SearchFragment
import kotlinx.android.synthetic.main.home_screen_fargment.*
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import java.util.ArrayList
import androidx.core.content.ContextCompat.getSystemService

import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.ecomercesystem.cart.CartActivity
import com.example.ecomercesystem.product_detail.ProductActivity


class HomeFragment : Fragment(R.layout.home_screen_fargment), ItemClickInterface,
        CategoriesHomeClickInterface {
    //    lateinit var viewModel: HomeViewModel
    lateinit var itemViewModel: ItemVIewModel
    lateinit var categoriesManager: RecyclerView.LayoutManager
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    val searchFragment = SearchFragment()
    val homeFullFragment = HomeFullFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = (activity as MainActivity).itemViewModel

        //setup rcv categories
        var data = listOf(
                HomeCategoriesItem("men", "null"),
                HomeCategoriesItem("women", "null"),
                HomeCategoriesItem("kids", "null")
        )
        categoriesManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categoriesAdapter = HomeCategoriesRcvAdapter(requireContext(), this)

        categoriesAdapter.list.addAll(data)
        rcv_categories_home?.apply {
            adapter = categoriesAdapter
            layoutManager = categoriesManager
        }


        //setup rcv items
        manager = GridLayoutManager(context, 2)
        val itemAdapter = ItemHomeAdapter(requireContext(), this)

        itemViewModel.recommendedItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)
            }
        })

        rcv_recommended?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }


        //chuyen tab categories
        btn_menu_toolbar?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                    .addToBackStack(null)
                    .commit()
        }

        //chuyen tab home full
        btn_see_all?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, homeFullFragment)
                    .addToBackStack(null)
                    .commit()
            itemViewModel.getAllItems()
        }

        //chuyen tab search
        btn_search_home.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_main, searchFragment)
                    .addToBackStack(null)
                    .commit()
        }
        btn_cart_home.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }
    }


    //item click cua rcv items
    override fun OnItemClick(item: Item) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }

    override fun OnFavorIconClick(item: Item) {
        itemViewModel.insertFavorItem(ItemFavor(item.name, item.imgsrc, item.price, item.rating))
        Toast.makeText(requireContext(), "Added to Favourites", Toast.LENGTH_SHORT).show()
    }

    //item click categories
    override fun onCategoriesHomeClick(item: HomeCategoriesItem) {

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
        transaction.replace(R.id.fragment_container_main, homeFullFragment)
                .addToBackStack(null)
                .commit()
        itemViewModel.getItemsByCategories(item.name)
    }
}