package com.example.month_7_lesson_1.shop_list

import androidx.room.Dao
import androidx.room.Query
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

@Dao
class ShopItemDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<ShopItem>

    @Query
}