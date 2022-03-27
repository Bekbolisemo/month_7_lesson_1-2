package com.example.month_7_lesson_1.shop_list.domain.usecases

import com.example.month_7_lesson_1.shop_list.domain.ShopListRepository
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}