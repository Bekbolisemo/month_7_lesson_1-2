package com.example.month_7_lesson_1.shop_list.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItem(
    @PrimaryKey val shopItemId:Int,
    val name: String?,
    val count: Int,
    var enable: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}