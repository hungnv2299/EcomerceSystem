package com.example.ecomercesystem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.databinding.HomeScreenFargmentBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import kotlinx.android.synthetic.main.home_screen_fargment.*

class HomeFragment : Fragment(R.layout.home_screen_fargment) {
    lateinit var viewModel: HomeViewModel
    lateinit var itemViewModel: ItemVIewModel
    lateinit var categoriesManager: RecyclerView.LayoutManager
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        itemViewModel = ViewModelProviders.of(this).get(ItemVIewModel::class.java)
//        viewModel.demo.observe(viewLifecycleOwner, object : Observer<Any>{
//            override fun onChanged(t: Any?) {
//                apparel.text = t?.toString()
//            }
//        })

        var data = listOf(
            HomeCategoriesItem("men", "null"),
            HomeCategoriesItem("women", "null"),
            HomeCategoriesItem("kids", "null")
        )
        categoriesManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_categories_home?.apply {
            adapter = HomeCategoriesRcvAdapter(data)
            layoutManager = categoriesManager
        }

        var dataItem = itemViewModel.allItems.value!!.toList()
//            listOf(
//            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
//            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
//            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz"),
//            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
//            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
//            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz")
//        )
//        manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        manager = GridLayoutManager(context, 2)
        rcv_recommended?.apply {
            adapter = ItemHomeAdapter(dataItem)
            layoutManager = manager
        }



        btn_menu_toolbar?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}