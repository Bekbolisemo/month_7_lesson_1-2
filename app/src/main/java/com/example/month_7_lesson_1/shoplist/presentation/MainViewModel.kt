package com.example.month_7_lesson_1.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.month_7_lesson_1.shoplist.data.ShopListRepositoryImpl
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem
import com.example.month_7_lesson_1.shoplist.domain.usecases.*

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()

    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val addShopItemUseCase = AddShopItemUseCase(repository)

    private val getShopListUseCase = GetShopListUseCase(repository)

    val shopListLD = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun getShopItem(shopItemId: Int): ShopItem{
       return getShopItemUseCase.getShopItem(shopItemId)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }
}