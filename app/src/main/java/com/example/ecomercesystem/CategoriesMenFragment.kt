package com.example.ecomercesystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ecomercesystem.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.home_full.HomeFullFragment
import kotlinx.android.synthetic.main.categories_men_fragment.*

class CategoriesMenFragment : Fragment(R.layout.categories_men_fragment) {
    val homeFullFragment = HomeFullFragment()
    lateinit var itemViewModel: ItemVIewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = (activity as MainActivity).itemViewModel
        fun changeFragment(){
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, homeFullFragment)
//                .addToBackStack(null)
                .commit()
        }
        btn_categories_men_tshirt.setOnClickListener {
            Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
            itemViewModel.getItemsByType("men", "shirt")
            changeFragment()
        }
    }
}