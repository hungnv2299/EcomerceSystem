package com.example.ecomercesystem.data.local

import androidx.lifecycle.LiveData
import com.example.ecomercesystem.data.model.Item

class ItemRepository(private val itemDAO: ItemDAO) {
    val allItems:LiveData<List<Item>> = itemDAO.selectAllItems()
    suspend fun addItem(item: Item){
        itemDAO.addItem(item)
    }

    suspend fun deleteItem(item: Item){
        itemDAO.deleteItem(item)
    }

}