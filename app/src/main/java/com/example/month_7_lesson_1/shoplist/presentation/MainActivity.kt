package com.example.month_7_lesson_1.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.databinding.ActivityMainBinding
import com.example.month_7_lesson_1.shoplist.domain.entities.ShopItem
import com.example.month_7_lesson_1.shoplist.extanhion.toast

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private var shopItemListId = mutableListOf<ShopItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
            binding.tvText.text = shopItemListId.toString()
            Log.e("shamal", "ShopList: $it")
        }
    }
    private fun initListeners() = with(binding) {
        addBtn.setOnClickListener {
            val item = ShopItem(getString(R.string.cucumbers), 2, false)
            shopItemListId.add(item)
            viewModel.addShopItem(item)
            show()
        }
        deleteBtn.setOnClickListener {
            val reversedShopItem = shopItemListId.reversed()
            viewModel.deleteShopItem(reversedShopItem[0])
            show()
        }
        showIdBtn.setOnClickListener {
            val shopItem = viewModel.getShopItem(editText.text.toString().toInt())
            toast(getString(R.string.id_item) + shopItem.id.toString())
            show()
        }
        showBtn.setOnClickListener {
            show()
        }
        editBtn.setOnClickListener {
            val reversedShopItem = shopItemListId.reversed()
            val item = ShopItem(editText.text.toString(),2,false)
            val s = shopItemListId.indices
            for (i in s){
                if (reversedShopItem[0] == shopItemListId[i]){
                    shopItemListId[i] = item
                }
            }
            viewModel.editShopItem(item)
            show()
        }

    }

    private fun show (){
        viewModel.getShopList()
    }
}
