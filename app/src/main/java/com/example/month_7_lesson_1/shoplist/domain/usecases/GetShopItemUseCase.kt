package com.example.month_7_lesson_1.shoplist.domain.usecases

import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId: Int): ShopItem{
       return shopListRepository.getShopItem(shopItemId)
    }
}