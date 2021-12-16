package com.example.ecomercesystem.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
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
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.android.synthetic.main.home_screen_fargment.*
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.ArrayList
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService




class SearchFragment : Fragment(R.layout.search_fragment), ItemClickInterfaceSearch {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = (activity as MainActivity).itemViewModel



//        et_search.requestFocus()
        if (et_search.requestFocus()){
            var imm:InputMethodManager = (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }


        et_search.doAfterTextChanged {

                itemViewModel.searchByString(et_search.text.toString())


        }


        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemSearchAdapter(requireContext(), this)

//        if (itemViewModel.searchItems.value.isNullOrEmpty()){
//            itemViewModel.searchItems.postValue(null)
//        }
//        else{
//
//        }
        itemViewModel.searchItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)

                Toast.makeText(context, "Update View", Toast.LENGTH_SHORT).show()
            }
        })

        rcv_search?.apply {
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

    override fun OnFavorIconClick(item: Item) {
        Toast.makeText(requireContext(), "Added "+item.name+" to favourite", Toast.LENGTH_SHORT).show()
        itemViewModel.insertFavorItem(ItemFavor(item.name, item.imgsrc, item.price, item.rating))
    }

}