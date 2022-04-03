package com.example.month_7_lesson_1.shop_list.presentation.second_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.databinding.ActivitySecondBinding
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem
import com.example.month_7_lesson_1.shop_list.extanhion.toast
import com.example.month_7_lesson_1.shop_list.presentation.main_activity.MainViewModel

class SecondActivity : AppCompatActivity(),OnClickListener,OnLongClickListener {
    private val viewModel: SecondViewModel by viewModels()
    private lateinit var recycler:RecyclerView
    private val binding: ActivitySecondBinding by viewBinding()
    private val shopList = mutableListOf<ShopItem>()
    private var pos:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        addAllRecycler()
        initRecyclerView()
        initObservers()
    }

    private fun addAllRecycler() {
        var id = 0
        for (i in 0..50){
            val item = ShopItem("asd",5,false,id++)
            val random = (0..1).random()
            if (random == 1){
                item.enable = true
            }
            if (!viewModel.shopListLD.value.isNullOrEmpty()){

            }
            shopList.add(item)
            viewModel.addShopItem(shopList[i])
        }
    }

    private fun initRecyclerView() {
        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = SecondActivityAdapter(shopList,this,this)
        pos = adapter.getPos()
        recycler.adapter = adapter

    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
            Log.e("shamal", "ShopList: $it")
        }
    }

    override fun onClickListener(shopItem: ShopItem,pos:Int) {
        val shopItem = viewModel.getShopItem(pos)
        toast(getString(R.string.id_item) + shopItem.toString())
    }

    override fun onLongClickListener(shopItem: ShopItem,pos:Int) {
     viewModel.changeEnableState(shopList[pos])
        toast(getString(R.string.enable) + shopList[pos].enable)
    }
}