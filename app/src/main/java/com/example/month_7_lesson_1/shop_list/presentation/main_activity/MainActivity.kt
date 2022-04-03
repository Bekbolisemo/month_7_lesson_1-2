package com.example.month_7_lesson_1.shop_list.presentation.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.databinding.ActivityMainBinding
import com.example.month_7_lesson_1.shop_list.extanhion.toast
import com.example.month_7_lesson_1.shop_list.presentation.main_activity.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.example.month_7_lesson_1.shop_list.presentation.main_activity.ShopListAdapter.Companion.VIEW_TYPE_ENABLED

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private  var shopListAdapter = ShopListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       initObservers()
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.mainRv.run {
            adapter = shopListAdapter
        }
        setupSwipeListener(binding.mainRv)

        setupOnLongClickListeners()

        setupListeners()
    }

    private fun setupOnLongClickListeners() {
        shopListAdapter.onShopItemLongClickListener = { position ->
            toast(position.toString())
        }
    }

    private fun setupListeners() {
        shopListAdapter.onShopItemClickListener = { shopItem, enabled ->
            if (enabled) {
                shopItem.enable = false
                VIEW_TYPE_DISABLED
            } else {
                shopItem.enable = true
                VIEW_TYPE_ENABLED
            }
        }
    }

    private fun setupSwipeListener(mainRv: RecyclerView) {
        val callBack = object :ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(mainRv)
    }

    private fun initObservers() {
        viewModel.shopListLD.observe(this) {
           /* binding.tvText.text = shopItemListId.toString()*/
            Log.e("shamal", "ShopList: $it")
            shopListAdapter.shopList = it
        }
    }

    /*
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

       }*/

}
