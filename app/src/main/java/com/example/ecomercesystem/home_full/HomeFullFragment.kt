package com.example.ecomercesystem.home_full

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
import com.example.ecomercesystem.databinding.HomeScreenFargmentBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import kotlinx.android.synthetic.main.home_screen_fargment.*
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import java.util.ArrayList

class HomeFullFragment : Fragment(R.layout.home_screen_full_fragment), ItemClickInterfaceFull {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        itemViewModel = (activity as MainActivity).itemViewModel
//        itemViewModel.test.observe(viewLifecycleOwner, object : Observer<Any> {
//            override fun onChanged(t: Any?) {
//                apparel.text = t?.toString()
//            }
//        })

        apparel_full.setOnClickListener {
            itemViewModel.getItemsByCategories("men")
        }



        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemHomeFullAdapter(requireContext(), this)
        itemViewModel.allItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)
            }
        })

        rcv_item_home_full?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }


        //chuyen tab categories
        btn_menu_toolbar_full?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    //item click cua rcv items
    override fun OnItemClick(item: Item) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }
    //click add btn
    override fun OnAddBtnClick(item: Item) {
        Toast.makeText(requireContext(), "Added "+item.name+" to cart", Toast.LENGTH_SHORT).show()
        itemViewModel.addToCart(ItemCart(item.name, item.imgsrc, item.price, 1))
    }

}