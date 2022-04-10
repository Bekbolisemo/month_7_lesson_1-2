package com.example.month_7_lesson_1.shop_list.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopItemEntity::class],version = 1,exportSchema = false)
abstract class ShopItemDataBase : RoomDatabase() {

   abstract fun shopListDao() : ShopItemDao

}