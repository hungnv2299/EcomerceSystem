package com.example.ecomercesystem.home

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

class ItemHomeAdapter(private val data:List<Item>) : RecyclerView.Adapter<ItemHomeAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
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
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recommended_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imgsrc).into(iv_item_recommended_home)
            tv_name_recommended_home.text = item.name
            tv_price_recommended_home.text = "$"+item.price.toString()
//            setOnClickListener {
//                onItemClickListener?.let { it(item) }
//            }
        }
    }

//    private var onItemClickListener:((Item) -> Unit)? = null
//
//    fun setOnItemClickListener(listener : (Item) -> Unit){
//        onItemClickListener = listener
//    }
    override fun getItemCount(): Int {
        return data.size
    }


}