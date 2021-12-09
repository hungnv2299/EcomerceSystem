package com.example.ecomercesystem.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.ecomercesystem.data.model.Item
@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)
    @Query("SELECT * FROM items")
    fun selectAllItems(): LiveData<List<Item>>
    @Query("SELECT * FROM items WHERE category = :categories")
    fun selectItemsbyCategories(categories:String):LiveData<List<Item>>
    @Query("SELECT * FROM items WHERE type = :type")
    fun selectItemsbyType(type:String):LiveData<List<Item>>
    @Delete
    suspend fun deleteItem(item: Item)

}