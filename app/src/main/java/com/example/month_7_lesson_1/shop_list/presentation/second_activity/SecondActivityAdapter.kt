package com.example.month_7_lesson_1.shop_list.presentation.second_activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.month_7_lesson_1.databinding.ItemRecyclerBinding
import com.example.month_7_lesson_1.shop_list.domain.entities.ShopItem

class SecondActivityAdapter(
    private val list: MutableList<ShopItem>,
    private val onClick: OnClickListener,
    private val onLongClick: OnLongClickListener
) : RecyclerView.Adapter<SecondActivityAdapter.ViewHolder>() {
    private var position:Int = 0
    class ViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ShopItem) {
            binding.TextView.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.onBind(list[position])
        val itemPos = list[position]
        this.position = position
        holder.itemView.setOnClickListener {
            onClick.onClickListener(itemPos,position)
        }
        holder.itemView.setOnLongClickListener{
            onLongClick.onLongClickListener(itemPos,position)
            true
        }
    }
   fun getPos(): Int {
        return position
    }

    override fun getItemCount(): Int = list.size
}