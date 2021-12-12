package com.example.ecomercesystem.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.R
import com.example.ecomercesystem.databinding.ItemCategotiesHomeBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import kotlinx.android.synthetic.main.item_categoties_home.view.*

class HomeCategoriesRcvAdapter(
    val context:Context,
    val categoriesHomeClickListener:CategoriesHomeClickInterface):RecyclerView.Adapter<HomeCategoriesRcvAdapter.HomeCategoriesRcvViewHolder>() {

    val list = ArrayList<HomeCategoriesItem>()

    inner class HomeCategoriesRcvViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

//        fun bind(item: HomeCategoriesItem){
//            binding.categoriesItem = item
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesRcvViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_categoties_home,
            parent,
            false
        )
        return HomeCategoriesRcvViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeCategoriesRcvViewHolder, position: Int) {
        val itemRcm = list[position]
        holder.itemView.apply {
            tv_categories_home.text = itemRcm.name
            setOnClickListener {
                categoriesHomeClickListener.onCategoriesHomeClick(itemRcm)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
interface CategoriesHomeClickInterface{
    fun onCategoriesHomeClick(item:HomeCategoriesItem)
}