package com.example.architecturalpattern

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturalpattern.model.Item


class WishListAdapter(private val wishList: ArrayList<Item> = arrayListOf()) :
    RecyclerView.Adapter<WishListAdapter.ItemViewHolder>() {


    // 리스트 갱신
    fun submitList(newList: List<Item>) {
        Log.d("++Adapter", wishList.toString())
        wishList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(wishList[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = wishList.size


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(item: Item, position: Int) {
            itemName.text = item.name
            itemPrice.text = item.price.toString()
        }
    }
}