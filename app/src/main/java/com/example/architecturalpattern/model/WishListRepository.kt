package com.example.architecturalpattern.model

import kotlinx.coroutines.delay

class WishListRepository {
    private val _wishList: MutableList<Item> = mutableListOf()
    val wishList: List<Item> get() = _wishList

    init {
        _wishList.add(Item("테스트1",1000))
        _wishList.add(Item("테스트2",1000))
        _wishList.add(Item("테스트3",1000))
    }

    // db나 서버에서 데이터 받아오기
    suspend fun getWishList(): List<Item> {
        delay(1000L)
        return wishList
    }

    fun addWish(item: Item) {
        _wishList.add(item)
    }

    fun removeItem(item: Item) {
        _wishList.remove(item)
    }
}