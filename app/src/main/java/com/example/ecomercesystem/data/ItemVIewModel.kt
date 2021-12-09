package com.example.ecomercesystem.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomercesystem.data.local.ItemDB
import com.example.ecomercesystem.data.local.ItemRepository
import com.example.ecomercesystem.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemVIewModel(application: Application):AndroidViewModel(application) {
    private val allItems : LiveData<List<Item>>
    private val repository:ItemRepository

    init {
        val dao = ItemDB.getDB(application).itemDAO()
        repository = ItemRepository(dao)
        allItems = repository.allItems
    }

    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItem(item)
    }
    fun addItem(item: Item) = viewModelScope.launch(Dispatchers.IO){
        repository.addItem(item)
    }
}