package com.example.month_7_lesson_1.shoplist.domain.usecases

import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}