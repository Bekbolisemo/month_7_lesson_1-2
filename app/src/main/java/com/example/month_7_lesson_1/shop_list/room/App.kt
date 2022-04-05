package com.example.month_7_lesson_1.shop_list.room

import android.app.Application
import androidx.room.Room

class App : Application() {

    companion object {
        lateinit var dataBase: ShopItemDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            applicationContext,
            ShopItemDataBase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()

    }

}