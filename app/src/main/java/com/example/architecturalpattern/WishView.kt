package com.example.architecturalpattern

import com.example.architecturalpattern.model.Item


interface WishView {
    fun notifyWishListChanged(wishList: List<Item>)
    fun showProgress()
    fun hideProgress()

}