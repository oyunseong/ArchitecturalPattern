package com.example.architecturalpattern

import com.example.architecturalpattern.model.Item
import com.example.architecturalpattern.model.WishListRepository

class WishListController(
    private val wishListRepository: WishListRepository,
    private val wishView: WishView,
) {
    fun addItem(item: Item) {
        wishListRepository.addWish(item = item)
        wishView.notifyWishListChanged(wishListRepository.wishList)
    }

    fun removeItem(item: Item) {
        wishListRepository.removeItem(item)
        wishView.notifyWishListChanged(wishListRepository.wishList)
    }

    suspend fun getWishList(): List<Item> {
        wishView.showProgress()
        val wishList = wishListRepository.getWishList()
        wishView.hideProgress()
        return wishList
    }

}