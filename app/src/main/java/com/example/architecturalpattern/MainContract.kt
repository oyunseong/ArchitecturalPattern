package com.example.architecturalpattern

import com.example.architecturalpattern.model.Item

interface MainContract {

    interface WishView {
        fun notifyWishListChanged(wishList: List<Item>)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun addItem(item: Item)
        fun removeItem(item: Item)
        suspend fun getWishList(): List<Item>
    }
}