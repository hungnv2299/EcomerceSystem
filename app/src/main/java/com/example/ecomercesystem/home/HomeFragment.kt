package com.example.ecomercesystem.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.ProductActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.categories.CategoriesFragment
import com.example.ecomercesystem.data.ItemVIewModel
import com.example.ecomercesystem.databinding.HomeScreenFargmentBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemFavor
import com.example.ecomercesystem.home_full.HomeFullFragment
import kotlinx.android.synthetic.main.home_screen_fargment.*
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.home_screen_fargment), ItemClickInterface,
    CategoriesHomeClickInterface {
    //    lateinit var viewModel: HomeViewModel
    lateinit var itemViewModel: ItemVIewModel
    lateinit var categoriesManager: RecyclerView.LayoutManager
    lateinit var manager: RecyclerView.LayoutManager
    val categoriesFragment = CategoriesFragment()
    val homeFullFragment = HomeFullFragment()
    lateinit var dataItem: List<Item>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        itemViewModel = ViewModelProvider(this).get(ItemVIewModel::class.java)
        itemViewModel = (activity as MainActivity).itemViewModel
        itemViewModel.test.observe(viewLifecycleOwner, object : Observer<Any> {
            override fun onChanged(t: Any?) {
                apparel.text = t?.toString()
            }
        })

        apparel.setOnClickListener {
            itemViewModel.insertItem(Item("Áo Hoodie Nam", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "men", "hoodie", 399.0, 4.1, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Áo Hoodie Nữ", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "women", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Quần Jeans Nam", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "men", "jeans", 200.0, 2.5, "abcxyz"))
            itemViewModel.insertItem(Item("Quần Jeans Nữ", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "women", "jeans", 200.0, 2.5, "abcxyz"))
            itemViewModel.insertItem(Item("Áo Hoodie Nam", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "men", "hoodie", 399.0, 4.1, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Áo Hoodie Nữ", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "women", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Quần Jeans Nam", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "men", "jeans", 200.0, 2.5, "abcxyz"))
            itemViewModel.insertItem(Item("Quần Jeans Nữ", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "women", "jeans", 200.0, 2.5, "abcxyz"))
            itemViewModel.insertItem(Item("Áo Hoodie Kids", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "kids", "hoodie", 399.0, 4.1, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Áo Hoodie Kids", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "kids", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            itemViewModel.insertItem(Item("Quần Jeans Kids", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "kids", "jeans", 200.0, 2.5, "abcxyz"))
            itemViewModel.insertItem(Item("Quần Jeans Kids", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "kids", "jeans", 200.0, 2.5, "abcxyz"))

        }


        //setup rcv categories
        var data = listOf(
            HomeCategoriesItem("men", "null"),
            HomeCategoriesItem("women", "null"),
            HomeCategoriesItem("kids", "null")
        )
        categoriesManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categoriesAdapter = HomeCategoriesRcvAdapter(requireContext(), this)

        categoriesAdapter.list.addAll(data)
        rcv_categories_home?.apply {
            adapter = categoriesAdapter
            layoutManager = categoriesManager
        }


        //setup rcv items
        manager = GridLayoutManager(context, 2)
        val itemAdapter = ItemHomeAdapter(requireContext(), this)

        itemViewModel.recommendedItems.observe(viewLifecycleOwner, { list ->
            list?.let {
                itemAdapter.updateList(list)
            }
        })

        rcv_recommended?.apply {
            adapter = itemAdapter
            layoutManager = manager
        }


        //chuyen tab categories
        btn_menu_toolbar?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, categoriesFragment)
                .addToBackStack(null)
                .commit()
        }

        //chuyen tab home full
        btn_see_all?.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            transaction.replace(R.id.fragment_container_main, homeFullFragment)
                .addToBackStack(null)
                .commit()
            itemViewModel.getAllItems()
        }
    }

    //item click cua rcv items
    override fun OnItemClick(item: Item) {
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)
    }

    override fun OnFavorIconClick(item: Item) {
        itemViewModel.insertFavorItem(ItemFavor(item.name, item.imgsrc, item.price, item.rating))
        Toast.makeText(requireContext(), "Added to Favourites", Toast.LENGTH_SHORT).show()
    }

    //item click categories
    override fun onCategoriesHomeClick(item: HomeCategoriesItem) {
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_SHORT).show()

        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
        transaction.replace(R.id.fragment_container_main, homeFullFragment)
            .addToBackStack(null)
            .commit()
        itemViewModel.getItemsByCategories(item.name)
    }
}