package com.example.ecomercesystem.data

import android.app.Application
import androidx.lifecycle.*
import com.example.ecomercesystem.data.local.ItemDB
import com.example.ecomercesystem.data.local.ItemRepository
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.data.model.ItemFavor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemVIewModel(application: Application) : AndroidViewModel(application) {
    var allItems: MutableLiveData<List<Item>> = MutableLiveData()
    var cartItems: MutableLiveData<List<ItemCart>> = MutableLiveData()
    var favorItems: MutableLiveData<List<ItemFavor>> = MutableLiveData()
    var recommendedItems: MutableLiveData<List<Item>> = MutableLiveData()
    var selectedItems: MutableLiveData<List<Item>> = MutableLiveData()
    var test: MutableLiveData<String> = MutableLiveData()
    var categories: MutableLiveData<String> = MutableLiveData()

    init {
        test.postValue("test viewmodel")
        getRecommendedItems()
        getAllItems()
        getFavorItems()
        getCartItems()
//                allItems.postValue(listOf(
//            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
//            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
//            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz"),
//            Item("Ao adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 500.0, 4.5, "abcxyz"),
//            Item("Quan adidas", "https://cf.shopee.vn/file/78c9f55d224e5f817bffaf04670752c7", "men", "shirt", 50.0, 4.5, "abcxyz"),
//            Item("tat adidas", "https://balovnxk.vn/wp-content/uploads/2017/06/balo-adidas-1.jpg", "men", "shirt", 5.0, 4.5, "abcxyz")))
    }

    fun getAllItemsObservers(): MutableLiveData<List<Item>> {
        return allItems
    }

    fun getFavorItems() {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectAllFavor()

        favorItems.postValue(list)
    }

    fun getCartItems() {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectAllCart()

        cartItems.postValue(list)
    }
    fun getAmountCart(): Int? {
        return cartItems.value?.size
    }

    fun getAllItems() {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectAllItems()

        allItems.postValue(list)
    }

    fun getItemByName(name:String):Item {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectByName(name)
        return list[0]
//        selectedItems.postValue(list)
    }

    fun getItemsByCategories(category:String) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectItemsbyCategories(category)

        allItems.postValue(list)
    }

    fun getItemsByType(category:String, type:String) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectItemsbyType(category, type)

        allItems.postValue(list)
    }

    fun getRecommendedItems() {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectRecommended()

        recommendedItems.postValue(list)
    }

    fun insertItem(item: Item) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.addItem(item)
        getAllItems()
    }

    fun deleteItem(item: Item) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.deleteItem(item)
        getAllItems()
    }

    fun insertFavorItem(itemFavor: ItemFavor) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.addToFavor(itemFavor)

        getFavorItems()
    }

    fun deleteFavorItem(itemFavor: ItemFavor) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.deleteFromFavor(itemFavor)

        getFavorItems()
    }

    fun addToCart(itemCart: ItemCart) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.addToCart(itemCart)

        getCartItems()
    }

    fun deleteFromCart(itemCart: ItemCart) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.deleteFromCart(itemCart)

        getCartItems()
    }

    fun getSubTotal():Double{
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        return dao.selectSumCart()
    }

    fun getAmount():Int?{
        return cartItems.value?.size
    }

    fun itemCartMinus(name: String){
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.itemCartMinus(name)

        getCartItems()
    }
    fun itemCartPlus(name: String){
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.itemCartPlus(name)

        getCartItems()
    }







}