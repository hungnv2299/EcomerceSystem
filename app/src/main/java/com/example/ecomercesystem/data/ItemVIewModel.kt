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
    var searchItems: MutableLiveData<List<Item>> = MutableLiveData()
    var cartItems: MutableLiveData<List<ItemCart>> = MutableLiveData()
    var favorItems: MutableLiveData<List<ItemFavor>> = MutableLiveData()
    var recommendedItems: MutableLiveData<List<Item>> = MutableLiveData()
    var selectedItems: MutableLiveData<List<Item>> = MutableLiveData()
    var test: MutableLiveData<String> = MutableLiveData()
    var categories: MutableLiveData<String> = MutableLiveData()

    init {
        var isInserted = isDBInserted()
        if (isInserted < 1) {
            insertItem(Item("Áo Tshirt Nam", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "men", "shirt", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Áo Hoodie Nam", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "men", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Quần Jeans Nam", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "men", "jeans", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Quần Shorts Nam", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "men", "shorts", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Áo Len Nam", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "men", "sweater", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Quần Thể Thao Nam", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "men", "tracks", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Tất Nam", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "men", "socks", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Đồ lót nam", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "men", "underwear", 200.0, 2.5, "abcxyz"))

            insertItem(Item("Áo Tshirt Nữ", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "women", "shirt", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Áo Hoodie Nữ", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "women", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Quần Jeans Nữ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "women", "jeans", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Quần Shorts Nữ", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "women", "shorts", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Áo Len Nữ", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "women", "sweater", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Quần Thể Thao Nữ", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "women", "tracks", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Tất Nữ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "women", "socks", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Đồ lót Nữ", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "women", "underwear", 200.0, 2.5, "abcxyz"))

            insertItem(Item("Áo Tshirt Trẻ em", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "kids", "shirt", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Áo Hoodie Trẻ em", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "kids", "hoodie", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Quần Jeans Trẻ em", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "kids", "jeans", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Quần Shorts Trẻ em", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "kids", "shorts", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Áo Len Trẻ em", "https://www.buytshirtsonline.co.uk/images/mens-anthem-hoodie-p11285-237976_medium.jpg", "kids", "sweater", 399.0, 4.1, "This is a Hoodie"))
            insertItem(Item("Quần Thể Thao Trẻ em", "https://www.ikea.com/au/en/images/products/eftertraeda-hoodie-white__0932916_pe791670_s5.jpg?f=s", "kids", "tracks", 399.0, 4.5, "This is a Hoodie"))
            insertItem(Item("Tất Trẻ em", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFx-VPNIETzEiGUnCiqsp3mK7gkGd-8ZOgwdGaYu3p7AijVPj8KOSfpaqM_pi_oiuA-fY&usqp=CAU", "kids", "socks", 200.0, 2.5, "abcxyz"))
            insertItem(Item("Đồ lót Trẻ em", "https://st.mngbcn.com/rcs/pics/static/T1/fotos/outfit/S20/17010609_TC-99999999_01.jpg?ts=1619017511413&imwidth=388&imdensity=2", "kids", "underwear", 200.0, 2.5, "abcxyz"))

        }
        getRecommendedItems()
        getAllItems()
        getFavorItems()
        getCartItems()
    }

    fun getAllItemsObservers(): MutableLiveData<List<Item>> {
        return allItems
    }

    fun isDBInserted(): Int {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        return dao.isDBInserted()
    }

    fun getFavorItems() {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectAllFavor()

        favorItems.postValue(list)
    }

    fun deleteCart(){
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.deleteAllFromCart()
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

    fun getItemByName(name: String): Item {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectByName(name)
        return list[0]
//        selectedItems.postValue(list)
    }

    fun getItemsByCategories(category: String) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.selectItemsbyCategories(category)

        allItems.postValue(list)
    }

    fun getItemsByType(category: String, type: String) {
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

    fun getSubTotal(): Double {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        return dao.selectSumCart()
    }

    fun getAmount(): Int {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        return dao.selectSumAmount()
    }

    fun itemCartMinus(itemCart: ItemCart) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        if (itemCart.amount <= 1)
            deleteFromCart(itemCart)
        dao.itemCartMinus(itemCart.name)

        getCartItems()
    }

    fun itemCartPlus(name: String) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        dao.itemCartPlus(name)

        getCartItems()
    }

    fun searchByString(string: String) {
        val dao = ItemDB.getDB(getApplication()).itemDAO()
        val list = dao.searchByString(string)

        searchItems.postValue(list)
    }

    fun removeSearchList() {
        searchItems.postValue(listOf())
    }


}