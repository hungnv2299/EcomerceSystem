package com.example.ecomercesystem.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.R
import com.example.ecomercesystem.databinding.ItemCategotiesHomeBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import kotlinx.android.synthetic.main.item_categoties_home.view.*

class HomeCategoriesRcvAdapter(private val data:List<HomeCategoriesItem>):RecyclerView.Adapter<HomeCategoriesRcvAdapter.HomeCategoriesRcvViewHolder>() {

    inner class HomeCategoriesRcvViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

//        fun bind(item: HomeCategoriesItem){
//            binding.categoriesItem = item
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesRcvViewHolder {
        return HomeCategoriesRcvViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_categoties_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeCategoriesRcvViewHolder, position: Int) {
        val itemRcm = data[position]
        holder.itemView.apply {
            tv_categories_home.text = itemRcm.name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}