package com.example.ecomercesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecomercesystem.cart.CartActivity
import com.example.ecomercesystem.cart.CartFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.favourite.FavouriteFragment
import com.example.ecomercesystem.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val favoriteFragment = FavouriteFragment()
    lateinit var itemViewModel: ItemVIewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        loadFragment(homeFragment)
        bottom_nav_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_bottom_nav_main_home -> loadFragment(homeFragment)
                R.id.menu_bottom_nav_main_favorite -> {
                    loadFragment(favoriteFragment)

                }
                R.id.menu_bottom_nav_main_cart -> {
//                    loadFragment(checkoutFragment)
                    itemViewModel.getCartItems()
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)

                }
            }
            true
        }



    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_main, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

}