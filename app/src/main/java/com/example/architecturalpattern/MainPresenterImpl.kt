package com.example.architecturalpattern

import com.example.architecturalpattern.model.Item
import com.example.architecturalpattern.model.WishListRepository

class MainPresenterImpl(private val wishView: MainContract.WishView) : MainContract.Presenter {
    private val wishListRepository = WishListRepository()
    override fun addItem(item: Item) {
        wishListRepository.addWish(item = item)
        wishView.notifyWishListChanged(wishListRepository.wishList)
    }

    override fun removeItem(item: Item) {
        wishListRepository.removeItem(item)
        wishView.notifyWishListChanged(wishListRepository.wishList)
    }

    override suspend fun getWishList(): List<Item> {
        wishView.showProgress()
        val wishList = wishListRepository.getWishList()
        wishView.notifyWishListChanged(wishList)
        wishView.hideProgress()
        return wishList
    }
}