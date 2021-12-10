package com.example.ecomercesystem.data

import android.app.Application
import androidx.lifecycle.*
import com.example.ecomercesystem.data.local.ItemDB
import com.example.ecomercesystem.data.local.ItemRepository
import com.example.ecomercesystem.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemVIewModel(val itemRepository: ItemRepository):ViewModel() {
    val allItems : MutableLiveData<List<Item>> = MutableLiveData()

    init {

    }



    fun deleteItem(item: Item) = viewModelScope.launch {
        itemRepository.deleteItem(item)
    }
    fun addItem(item: Item) = viewModelScope.launch{
        itemRepository.addItem(item)
    }
//    fun getAllItem() = viewModelScope.launch {
//        allItems.postValue(itemRepository.allItems)
//    }

    fun getAllItem() = viewModelScope.launch {
        allItems.postValue(listOf(
            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz"),
            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz")))
    }

}