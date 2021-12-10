package com.example.ecomercesystem.data.local

import androidx.lifecycle.LiveData
import com.example.ecomercesystem.data.model.Item

class ItemRepository(private val db:ItemDB) {
    val allItems:List<Item> = db.itemDAO().selectAllItems()
    suspend fun addItem(item: Item){
        db.itemDAO().addItem(item)
    }

    suspend fun deleteItem(item: Item){
        db.itemDAO().deleteItem(item)
    }

}