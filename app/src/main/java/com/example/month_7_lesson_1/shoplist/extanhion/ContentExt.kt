package com.example.month_7_lesson_1.shoplist.extanhion

import android.content.Context
import android.widget.Toast

fun Context.toast(massage:String){
    Toast.makeText(this,massage,Toast.LENGTH_LONG).show()
}