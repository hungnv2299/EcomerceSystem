package com.example.ecomercesystem.cart

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
import com.example.ecomercesystem.data.model.ItemCart
import kotlinx.android.synthetic.main.item_checkout_page.view.*


class ItemCartAdapter(
    val context: Context,
    val itemClickListener: ItemClickInterfaceCart
) :
    RecyclerView.Adapter<ItemCartAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val list = ArrayList<ItemCart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_checkout_page,
            parent,
            false
        )
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imgsrc).into(iv_item_cart)
            tv_checkout_item.text = item.name
            tv_checkout_price_item.text = "Rs. " + item.price.toString()
            tv_number.text = item.amount.toString()
            ic_delete.setOnClickListener {
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

    fun updateList(newList : List<ItemCart>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


}

interface ItemClickInterfaceCart {
    fun OnItemClick(item: ItemCart)
    fun OnDeleteBtnClick(item:ItemCart)
    fun OnPlusClick(item:ItemCart)
    fun OnMinusClick(item:ItemCart)
}


