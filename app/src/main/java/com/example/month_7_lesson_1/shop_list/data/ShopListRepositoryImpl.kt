package com.example.month_7_lesson_1.shop_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.month_7_lesson_1.shop_list.domain.ShopListRepository
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl() : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 50 ){

            val item = ShopItem("banana: $i",i,Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = shopList[shopItem.id]
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }


    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw  RuntimeException("Element with id $shopItemId not fount")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }

}