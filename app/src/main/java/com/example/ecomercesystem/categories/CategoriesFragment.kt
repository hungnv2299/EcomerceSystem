package com.example.ecomercesystem.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.ecomercesystem.R
import com.example.ecomercesystem.home.HomeFragment
import com.example.ecomercesystem.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.categories_fragment.*


class CategoriesFragment : Fragment() {
    lateinit var adapter: ViewPagerCategoriesAdapter
    val searchFragment = SearchFragment()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ViewPagerCategoriesAdapter(childFragmentManager, lifecycle)


        return inflater.inflate(R.layout.categories_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ViewPager2>(R.id.viewpager_categories).adapter = adapter
        TabLayoutMediator(tab_layout_categories, viewpager_categories){tab, position->
            when(position){
                0->{
                    tab.text = "Men"
                }
                1->{
                    tab.text = "Women"
                }
                2->{
                    tab.text = "Kids"
                }

            }
        }.attach()
        btn_back_toolbar_categories.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

        btn_search_categories.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_main, searchFragment)
                    .addToBackStack(null)
                    .commit()
        }


    }

}