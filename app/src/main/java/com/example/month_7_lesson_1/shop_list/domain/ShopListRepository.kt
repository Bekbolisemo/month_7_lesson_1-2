package com.example.month_7_lesson_1.shop_list.domain

import androidx.lifecycle.LiveData
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}