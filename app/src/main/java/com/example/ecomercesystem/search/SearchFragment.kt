package com.example.ecomercesystem.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.product_detail.ProductActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.android.synthetic.main.home_screen_full_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import android.view.inputmethod.InputMethodManager
<<<<<<< HEAD
import android.widget.EditText
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
=======
>>>>>>> 66a58caa0be0732f4d08241083dc97f7b7cc3520


class SearchFragment : Fragment(R.layout.search_fragment), ItemClickInterfaceSearch {
    lateinit var itemViewModel: ItemVIewModel
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    lateinit var dataItem: List<Item>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = (activity as MainActivity).itemViewModel

<<<<<<< HEAD
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
=======


            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        et_search.requestFocus()



        et_search.doAfterTextChanged {

>>>>>>> 66a58caa0be0732f4d08241083dc97f7b7cc3520
                itemViewModel.searchByString(et_search.text.toString())
            } else itemViewModel.removeSearchList()
        }
        //setup rcv items
        manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val itemAdapter = ItemSearchAdapter(requireContext(), this)

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