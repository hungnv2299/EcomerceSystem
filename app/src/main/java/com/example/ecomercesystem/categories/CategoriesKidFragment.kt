package com.example.ecomercesystem.categories

import android.os.Bundle
import android.view.View
import com.example.ecomercesystem.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.home_full.HomeFullFragment
import kotlinx.android.synthetic.main.categories_kid_fragment.*

class CategoriesKidFragment : Fragment(R.layout.categories_kid_fragment) {
    val homeFullFragment = HomeFullFragment()
    lateinit var itemViewModel: ItemVIewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = (activity as MainActivity).itemViewModel
        val fragmentManager = (activity as MainActivity).supportFragmentManager
        fun changeFragment(){
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, homeFullFragment)
                .addToBackStack(null)
                .commit()
        }
        btn_categories_kid_tshirt.setOnClickListener {
            itemViewModel.getItemsByType("kids", "shirt")
            changeFragment()
        }
        btn_categories_kid_hoodie.setOnClickListener {
            itemViewModel.getItemsByType("kids", "hoodie")
            changeFragment()
        }
        btn_categories_kid_jeans.setOnClickListener {
            itemViewModel.getItemsByType("kids", "jeans")
            changeFragment()
        }
        btn_categories_kid_shorts.setOnClickListener {
            itemViewModel.getItemsByType("kids", "shorts")
            changeFragment()
        }
        btn_categories_kid_sweater.setOnClickListener {
            itemViewModel.getItemsByType("kids", "sweater")
            changeFragment()
        }
        btn_categories_kid_pants.setOnClickListener {
            itemViewModel.getItemsByType("kids", "tracks")
            changeFragment()
        }
        btn_categories_kid_socks.setOnClickListener {
            itemViewModel.getItemsByType("kids", "socks")
            changeFragment()
        }
        btn_categories_kid_underwear.setOnClickListener {
            itemViewModel.getItemsByType("kids", "underwear")
            changeFragment()
        }
    }
}