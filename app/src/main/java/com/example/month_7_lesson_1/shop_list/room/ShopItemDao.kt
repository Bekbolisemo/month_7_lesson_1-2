package com.example.month_7_lesson_1.shop_list.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopItemDao {

    @Query("SELECT * FROM shop_item")
    fun getAll(): LiveData<List<ShopItemEntity>>

    @Query("SELECT * FROM shop_item")
    fun loadAllByIds():LiveData<List<ShopItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(item: ShopItemEntity)

    @Delete
    fun delete(item: ShopItemEntity)

    @Delete
    fun deleteAll(lisItem:ArrayList<ShopItemEntity>)

}