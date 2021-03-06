package com.example.ecomercesystem.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.data.model.ItemFavor
import com.example.ecomercesystem.product_detail.ProductActivity
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment(R.layout.search_fragment), ItemClickInterfaceSearch {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
    lateinit var dataItem: List<Item>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = (activity as MainActivity).itemViewModel

        et_search.requestFocus()
        et_search.postDelayed({
            var imm: InputMethodManager =
                    (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }, 100)

        et_search.doAfterTextChanged {
            if (!et_search.text.toString().isNullOrEmpty()) {
                itemViewModel.searchByString(et_search.text.toString())
            } else itemViewModel.removeSearchList()
        }
        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemSearchAdapter(requireContext(), this)

        itemViewModel.searchItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)

            }
        })

        rcv_search?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }

        btn_back_search_fragment.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
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
        Toast.makeText(requireContext(), "Added " + item.name + " to cart", Toast.LENGTH_SHORT)
                .show()
        itemViewModel.addToCart(ItemCart(item.name, item.imgsrc, item.price, 1))
    }

    override fun OnFavorIconClick(item: Item) {
        Toast.makeText(requireContext(), "Added " + item.name + " to favourite", Toast.LENGTH_SHORT)
                .show()
        itemViewModel.insertFavorItem(ItemFavor(item.name, item.imgsrc, item.price, item.rating))
    }

}