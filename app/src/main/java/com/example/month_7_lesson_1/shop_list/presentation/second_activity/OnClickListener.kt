package com.example.month_7_lesson_1.shop_list.presentation.second_activity

import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

interface OnClickListener {
    fun onClickListener(shopItem: ShopItem,pos:Int)
}