package com.example.ecomercesystem.home

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
import kotlinx.android.synthetic.main.item_recommended_home.view.*

class ItemHomeAdapter(
    val context: Context,
    val itemClickListener: ItemClickInterface
) :
    RecyclerView.Adapter<ItemHomeAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val list = ArrayList<Item>()
//
//    private val diffCallBack = object : DiffUtil.ItemCallback<Item>(){
//        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
//            return oldItem.name == newItem.name
//        }
//
//        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    val diff = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recommended_home,
            parent,
            false
        )
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imgsrc).into(iv_item_recommended_home)
            tv_name_recommended_home.text = item.name
            tv_price_recommended_home.text = "$" + item.price.toString()
            favorite_ic_recommended_home.setOnClickListener {
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

interface ItemClickInterface {
    fun OnItemClick(item: Item)
    fun OnFavorIconClick(item: Item)
}