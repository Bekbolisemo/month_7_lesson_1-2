package com.example.month_7_lesson_1.shop_list.room

import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

class Mapper{

    private fun shopItemEntityToShopItem(shopItemEntity: ShopItemEntity) = ShopItem(
        shopItemEntity.name,
        shopItemEntity.count,
        shopItemEntity.enable,
        shopItemEntity.id
    )
    fun shopItemToShopItemEntity(shopItem: ShopItem) = ShopItemEntity(
        shopItem.id,
        shopItem.name,
        shopItem.count,
        shopItem.enable
    )

    fun shopItemEntityListToShopItemList(list: List<ShopItemEntity>) = list.map {
        shopItemEntityToShopItem(it)
    }

    fun shopItemToShopItemEntityList(list:List<ShopItem>) = list.map {
        shopItemToShopItemEntity(it)
    }
}