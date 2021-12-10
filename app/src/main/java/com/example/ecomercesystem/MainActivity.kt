package com.example.ecomercesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var itemViewMdel:ItemVIewModel
    private val homeFragment = HomeFragment()
    private val favoriteFragment = CategoriesMenFragment()
    private val checkoutFragment = CheckoutFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(homeFragment)
        bottom_nav_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_bottom_nav_main_home -> loadFragment(homeFragment)
                R.id.menu_bottom_nav_main_favorite -> loadFragment(favoriteFragment)
                R.id.menu_bottom_nav_main_cart -> loadFragment(checkoutFragment)
            }
            true
        }

    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_main, fragment)
        transaction.commit()
    }

}