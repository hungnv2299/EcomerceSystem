package com.example.ecomercesystem.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.ecomercesystem.data.model.Item
import com.example.ecomercesystem.data.model.ItemCart
import com.example.ecomercesystem.data.model.ItemFavor

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)

    @Query("SELECT * FROM items")
    fun selectAllItems(): List<Item>

    @Query("SELECT * FROM items WHERE category = :categories")
    fun selectItemsbyCategories(categories: String): List<Item>

    @Query("SELECT * FROM items WHERE type = :type and category=:categories")
    fun selectItemsbyType(categories: String, type: String): List<Item>

    @Delete
    fun deleteItem(item: Item)

    @Query("SELECT * FROM items WHERE name=:name")
    fun selectByName(name: String): List<Item>

    @Query("SELECT * FROM items LIMIT 6")
    fun selectRecommended(): List<Item>

    @Insert
    fun addToFavor(itemFavor: ItemFavor)

    @Query("SELECT * FROM favourite")
    fun selectAllFavor(): List<ItemFavor>

    @Delete
    fun deleteFromFavor(itemFavor: ItemFavor)

    @Insert
    fun addToCart(itemCart: ItemCart)

    @Query("SELECT * FROM cart")
    fun selectAllCart(): List<ItemCart>

    @Delete
    fun deleteFromCart(itemCart: ItemCart)

<<<<<<< HEAD
    @Query("SELECT SUM(price) FROM cart")
    fun selectSumCart(): Double
=======
    @Query("SELECT SUM(price*amount) FROM cart")
    fun selectSumCart() : Double
>>>>>>> 66a58caa0be0732f4d08241083dc97f7b7cc3520

    @Query("SELECT SUM(amount) FROM cart")
    fun selectSumAmount(): Int

    @Query("update cart set amount = amount+1 where name=:name")
    fun itemCartPlus(name: String)

    @Query("update cart set amount = amount-1 where name=:name")
    fun itemCartMinus(name: String)

    @Query("SELECT * FROM items where name like '%'||:string||'%'")
    fun searchByString(string: String): List<Item>

//    fun blank():List<Item>

    @Query("SELECT Count(*) FROM items")
    fun isDBInserted(): Int

}