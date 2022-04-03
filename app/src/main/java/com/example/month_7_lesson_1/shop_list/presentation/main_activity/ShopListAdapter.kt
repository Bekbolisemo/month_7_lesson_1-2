package com.example.month_7_lesson_1.shop_list.presentation.main_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.month_7_lesson_1.R
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem
import java.lang.RuntimeException

class ShopListAdapter(
) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onShopItemClickListener: ((ShopItem,enabled:Boolean) -> Int)? = null
    var onShopItemLongClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enable) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        holder.onBind(item)
        holder.itemView.setOnClickListener {
           onShopItemClickListener?.invoke(item,item.enable)
            notifyItemChanged(position)
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(position)
            true
        }
    }

    override fun getItemCount() = shopList.size

    class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var productName: TextView = itemView.findViewById(R.id.tv_name)

        private var counter: TextView = itemView.findViewById(R.id.tv_count)

        fun onBind(shopItem: ShopItem) {
            productName.text = shopItem.name
            counter.text = shopItem.count.toString()
        }

    }

    companion object {

        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
    }
}