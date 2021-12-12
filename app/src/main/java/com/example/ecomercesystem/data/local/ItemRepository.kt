package com.example.ecomercesystem.data.local

import androidx.lifecycle.LiveData
import com.example.ecomercesystem.data.model.Item

class ItemRepository(private val dao:ItemDAO) {
    val allItems:List<Item> = dao.selectAllItems()
    suspend fun addItem(item: Item){
        dao.addItem(item)
    }

    suspend fun deleteItem(item: Item){
        dao.deleteItem(item)
    }

}