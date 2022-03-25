package com.example.month_7_lesson_1.shoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.month_7_lesson_1.shoplist.data.ShopListRepositoryImpl
import com.example.month_7_lesson_1.shoplist.domain.ShopListRepository
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem
import com.example.month_7_lesson_1.shoplist.domain.usecases.*

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()

    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val addShopItemUseCase = AddShopItemUseCase(repository)

    private val getShopListUseCase = GetShopListUseCase(repository)

    private val _shopListLD = MutableLiveData<List<ShopItem>>()

    val shopListLD: LiveData<List<ShopItem>>
        get() = _shopListLD

    fun getShopList() {
        _shopListLD.value = getShopListUseCase.getShopList()
    }

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun getShopItem(shopItemId: Int): ShopItem{
       return getShopItemUseCase.getShopItem(shopItemId)
    }

    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem)
    }
}