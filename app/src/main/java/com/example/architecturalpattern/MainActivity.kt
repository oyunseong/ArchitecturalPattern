package com.example.architecturalpattern

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturalpattern.databinding.ActivityMainBinding
import com.example.architecturalpattern.model.Item
import com.example.architecturalpattern.model.WishListRepository
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), MainContract.WishView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private lateinit var wishListAdapter: WishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenterImpl(this)
        loadWishList()
        initializeWishListRecyclerView()

        binding.addButton.setOnClickListener {
            onClickAddButton()
        }
        binding.removeButton.setOnClickListener {
            onClickRemoveButton()
        }
    }

    private fun onClickAddButton() {
        // 이름, 가격 입력받았다고 가정
//        wishListController.addWish(Item(name, price))
        presenter.addItem(Item("테스트", 100))
    }

    private fun onClickRemoveButton() {
        presenter.removeItem(Item("", 100))
    }

    override fun notifyWishListChanged(wishList: List<Item>) {
        wishListAdapter.submitList(wishList)
    }

    override fun showProgress() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressbar.visibility = View.GONE
    }

    private fun initializeWishListRecyclerView() {
        lifecycleScope.launch {
            wishListAdapter =
                WishListAdapter()// 안되는 이유 모르겠음 notifyDataSetChanged() 동작 방법을 알아야 할듯
            binding.itemRecyclerview.apply {
                adapter = wishListAdapter
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }

    }

    private fun loadWishList() {
        lifecycleScope.launch {
            presenter.getWishList()
        }
    }
}