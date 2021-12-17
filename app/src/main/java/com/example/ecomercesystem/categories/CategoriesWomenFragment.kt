package com.example.ecomercesystem.categories

import android.os.Bundle
import android.view.View
import com.example.ecomercesystem.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.home_full.HomeFullFragment


import kotlinx.android.synthetic.main.categories_women_fragment.*

class CategoriesWomenFragment : Fragment(R.layout.categories_women_fragment) {
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
        btn_categories_women_tshirt.setOnClickListener {
            itemViewModel.getItemsByType("women", "shirt")
            changeFragment()
        }
        btn_categories_women_hoodie.setOnClickListener {
            itemViewModel.getItemsByType("women", "hoodie")
            changeFragment()
        }
        btn_categories_women_jeans.setOnClickListener {
            itemViewModel.getItemsByType("women", "jeans")
            changeFragment()
        }
        btn_categories_women_shorts.setOnClickListener {
            itemViewModel.getItemsByType("women", "shorts")
            changeFragment()
        }
        btn_categories_women_sweater.setOnClickListener {
            itemViewModel.getItemsByType("women", "sweater")
            changeFragment()
        }
        btn_categories_women_pants.setOnClickListener {
            itemViewModel.getItemsByType("women", "tracks")
            changeFragment()
        }
        btn_categories_women_socks.setOnClickListener {
            itemViewModel.getItemsByType("women", "socks")
            changeFragment()
        }
        btn_categories_women_underwear.setOnClickListener {
            itemViewModel.getItemsByType("women", "underwear")
            changeFragment()
        }
    }
}