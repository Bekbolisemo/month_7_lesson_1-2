package com.example.month_7_lesson_1.shop_list.presentation.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.databinding.ActivityMainBinding
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem
import com.example.month_7_lesson_1.shop_list.extanhion.toast
import com.example.month_7_lesson_1.shop_list.presentation.main_activity.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.example.month_7_lesson_1.shop_list.presentation.main_activity.ShopListAdapter.Companion.VIEW_TYPE_ENABLED
import com.example.month_7_lesson_1.shop_list.room.App
import com.example.month_7_lesson_1.shop_list.room.Mapper
import com.example.month_7_lesson_1.shop_list.room.ShopItemEntity
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private  var shopListAdapter = ShopListAdapter()
    private lateinit var itemArrayList: ArrayList<ShopItem>
    private val mapper = Mapper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       initObservers()
        setupRecyclerView()
        initSearchView()
    }

 /*   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.item_toolbar,menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView= item?.actionView as SearchView


        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemArrayList.clear()
                val searchText = newText!!.toString().toInt()

                    shopListAdapter.shopList.forEach {
                        if (it.id == searchText){
                            itemArrayList.add(it)
                        }

                }
                shopListAdapter.notifyDataSetChanged()

                return false
            }

        })


        return super.onCreateOptionsMenu(menu)
    }*/

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(newText: String?): Boolean {


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
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
        viewModel.shopListLD.observe(this) { it ->
            Log.e("shamal", "ShopList: $it")
            shopListAdapter.shopList = it as ArrayList<ShopItem>
        }
    }

}
