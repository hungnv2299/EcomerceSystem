package com.example.ecomercesystem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.CategoriesKidFragment
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.databinding.HomeScreenFargmentBinding
import com.example.ecomercesystem.model.HomeCategoriesItem

class HomeFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: HomeScreenFargmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_screen_fargment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var data = listOf(
            HomeCategoriesItem("men", "null"),
            HomeCategoriesItem("women", "null"),
            HomeCategoriesItem("kids", "null")
        )
        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvCategoriesHome.apply {
            adapter = HomeCategoriesRcvAdapter(data)
            layoutManager = manager
        }

        binding.btnMenuToolbar.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
}