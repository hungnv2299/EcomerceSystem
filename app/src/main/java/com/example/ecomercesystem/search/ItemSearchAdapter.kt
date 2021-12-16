package com.example.ecomercesystem.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomercesystem.R
import com.example.ecomercesystem.data.model.HomeCategoriesItem
import com.example.ecomercesystem.data.model.Item
import kotlinx.android.synthetic.main.item_home_full.view.*
import kotlinx.android.synthetic.main.item_recommended_home.view.*

class ItemSearchAdapter(
    val context: Context,
    val itemClickListener: ItemClickInterfaceSearch
) :
    RecyclerView.Adapter<ItemSearchAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val list = ArrayList<Item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_home_full,
            parent,
            false
        )
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imgsrc).into(iv_item_home_full)
            tv_name_item_home_full.text = item.name
            tv_price_item_home_full.text = "Rs. " + item.price.toString()
            btn_add_item_home_full.setOnClickListener {
                itemClickListener.OnAddBtnClick(item)
            }
            iv_item_home_full.setOnClickListener {
                itemClickListener.OnFavorIconClick(item)
            }
            setOnClickListener {
                itemClickListener.OnItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList : List<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


}

interface ItemClickInterfaceSearch {
    fun OnItemClick(item: Item)
    fun OnAddBtnClick(item:Item)
    fun OnFavorIconClick(item:Item)
}


