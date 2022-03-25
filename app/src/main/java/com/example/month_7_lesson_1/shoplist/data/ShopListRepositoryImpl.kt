package com.example.month_7_lesson_1.shoplist.data

import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem

class ShopListRepositoryImpl() : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        val reversShopList = mutableListOf<ShopItem>()
        reversShopList.addAll(shopList)
        reversShopList.reverse()
        val s = shopList.indices
        for (i in s) {
            if (shopList[i].id == reversShopList[0].id) {
                shopList.removeAt(shopItem.id)
                reversShopList.clear()
                autoIncrementId--
            }
        }
    }

    override fun editShopItem(shopItem: ShopItem) {
        val reversShopList = mutableListOf<ShopItem>()
        reversShopList.addAll(shopList)
        reversShopList.reverse()
        reversShopList[0] = shopItem
    }


    override fun getShopItem(shopItemId: Int): ShopItem {
        val s = shopList.indices
        var index = 0
        for (i in s) {
            if (shopList[i].id == shopItemId) {
                index = i
            }
        }
        return shopList[index]
    }

    override fun getShopList(): List<ShopItem> {
        return shopList
    }

}