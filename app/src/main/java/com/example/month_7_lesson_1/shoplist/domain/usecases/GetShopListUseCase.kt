package com.example.month_7_lesson_1.shoplist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}