package com.example.month_7_lesson_1.shoplist.domain.entities

data class ShopItem(
    val name: String,
    val count: Int,
    var enable: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}