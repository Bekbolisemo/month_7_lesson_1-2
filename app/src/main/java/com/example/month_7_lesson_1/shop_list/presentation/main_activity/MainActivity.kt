package com.example.month_7_lesson_1.shop_list.presentation.main_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.databinding.ActivityMainBinding
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem
import com.example.month_7_lesson_1.shop_list.extanhion.toast
import com.example.month_7_lesson_1.shop_list.presentation.second_activity.SecondActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private var shopItemListId = mutableListOf<ShopItem>()
    private var putList = ArrayList<ShopItem>()

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
        }
        deleteBtn.setOnClickListener {
            val reversedShopItem = shopItemListId.reversed()
            viewModel.deleteShopItem(reversedShopItem[0])
        }
        showIdBtn.setOnClickListener {
            val shopItem = viewModel.getShopItem(editText.text.toString().toInt())
            toast(getString(R.string.id_item) + shopItem.id.toString())
        }
        showBtn.setOnClickListener {
        }
        editBtn.setOnClickListener {
            viewModel.changeEnableState(ShopItem(getString(R.string.cucumbers), 2, false, 0))
        }
        fabPut.setOnClickListener {
            putList.addAll(shopItemListId)
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

}
