package com.example.ecomercesystem.favourite

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
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.android.synthetic.main.item_favourite.view.*
import kotlinx.android.synthetic.main.item_home_full.view.*
import kotlinx.android.synthetic.main.item_home_full.view.iv_item_home_full
import kotlinx.android.synthetic.main.item_recommended_home.view.*

class ItemFavouriteAdapter(
    val context: Context,
    val itemClickListener: ItemClickInterfaceFavourite
) :
    RecyclerView.Adapter<ItemFavouriteAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val list = ArrayList<ItemFavor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_favourite,
            parent,
            false
        )
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imgsrc).into(iv_favourite)
            tv_favourite_name.text = item.name
            tv_favourite_price_item.text = "Rs. " + item.price.toString()
            tv_rating_favourite.text = item.rating.toString()+"/5"
            ic_delete_favourite.setOnClickListener {
                itemClickListener.OnDeleteBtnClick(item)
            }
            setOnClickListener {
                itemClickListener.OnItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList : List<ItemFavor>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


}

interface ItemClickInterfaceFavourite {
    fun OnItemClick(item: ItemFavor)
    fun OnDeleteBtnClick(item:ItemFavor)
}


