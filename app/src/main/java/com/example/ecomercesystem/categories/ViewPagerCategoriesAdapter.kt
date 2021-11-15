package com.example.ecomercesystem.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecomercesystem.CategoriesKidFragment
import com.example.ecomercesystem.CategoriesMenFragment
import com.example.ecomercesystem.CategoriesWomenFragment

class ViewPagerCategoriesAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                CategoriesMenFragment()
            }
            1->{
                CategoriesWomenFragment()
            }
            2->{
                CategoriesKidFragment()
            }
            else->{
                CategoriesMenFragment()
            }
        }

    }
}