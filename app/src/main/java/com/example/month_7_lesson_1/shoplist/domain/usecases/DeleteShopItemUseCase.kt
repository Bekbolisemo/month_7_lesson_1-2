package com.example.month_7_lesson_1.shoplist.domain.usecases

import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}