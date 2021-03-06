package com.example.ecomercesystem.categories

import android.os.Bundle
import android.view.View
import com.example.ecomercesystem.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.home_full.HomeFullFragment
import kotlinx.android.synthetic.main.categories_men_fragment.*

class CategoriesMenFragment : Fragment(R.layout.categories_men_fragment) {
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
        btn_categories_men_tshirt.setOnClickListener {
            itemViewModel.getItemsByType("men", "shirt")
            changeFragment()
        }
        btn_categories_men_hoodie.setOnClickListener {
            itemViewModel.getItemsByType("men", "hoodie")
            changeFragment()
        }
        btn_categories_men_jeans.setOnClickListener {
            itemViewModel.getItemsByType("men", "jeans")
            changeFragment()
        }
        btn_categories_men_shorts.setOnClickListener {
            itemViewModel.getItemsByType("men", "shorts")
            changeFragment()
        }
        btn_categories_men_sweater.setOnClickListener {
            itemViewModel.getItemsByType("men", "sweater")
            changeFragment()
        }
        btn_categories_men_pants.setOnClickListener {
            itemViewModel.getItemsByType("men", "tracks")
            changeFragment()
        }
        btn_categories_men_socks.setOnClickListener {
            itemViewModel.getItemsByType("men", "socks")
            changeFragment()
        }
        btn_categories_men_underwear.setOnClickListener {
            itemViewModel.getItemsByType("men", "underwear")
            changeFragment()
        }
    }
}