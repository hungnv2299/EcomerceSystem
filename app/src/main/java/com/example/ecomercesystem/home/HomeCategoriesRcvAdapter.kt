package com.example.ecomercesystem.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomercesystem.databinding.ItemCategotiesHomeBinding
import com.example.ecomercesystem.data.model.HomeCategoriesItem

class HomeCategoriesRcvAdapter(private val data:List<HomeCategoriesItem>):RecyclerView.Adapter<HomeCategoriesRcvAdapter.HomeCategoriesRcvViewHolder>() {

    inner class HomeCategoriesRcvViewHolder(private val binding:ItemCategotiesHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: HomeCategoriesItem){
            binding.categoriesItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesRcvViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = ItemCategotiesHomeBinding.inflate(inflater, parent, false)
        return HomeCategoriesRcvViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: HomeCategoriesRcvViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}