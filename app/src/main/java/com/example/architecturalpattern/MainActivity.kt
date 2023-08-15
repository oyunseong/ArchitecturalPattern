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


class MainActivity : AppCompatActivity(), WishView {
    private lateinit var binding: ActivityMainBinding

    private val wishListController: WishListController = WishListController(
        wishListRepository = WishListRepository(),
        wishView = this
    )
    private lateinit var wishListAdapter: WishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeWishListRecyclerView()
        loadWishList()

        binding.addButton.setOnClickListener {
            onClickAddButton()
            Log.d("++MainActivity", wishListController.getWishListLog().toString())
        }
        binding.removeButton.setOnClickListener {
            onClickRemoveButton()
        }

        val model = WishListRepository()
        val inputData = Item("", 0) // 입력 값
        binding.addButton.setOnClickListener {// 1. 클릭 이벤트 발생
            // setOnClickListener 내부는 컨트롤러 영역
            model.addWish(inputData)    // 2. 컨트롤러가 모델에게 데이터 전송 -> 3. 이후 모델은 비즈니스 로직을 통해 데이터를 업데이트함.
            val updatedData = model.wishList // 4. 컨트롤러는 업데이트된 데이터를 가져옴
            wishListAdapter.submitList(updatedData)  // 5. 컨트롤러는 뷰에게 값 전달 -> adapter(view) 6. ui를 업데이트 로직 수행
            // UI 업데이트 로직
        }
    }

    private fun onClickAddButton() {
        // 이름, 가격 입력받았다고 가정
        wishListController.addItem(Item("테스트", 100))
    }

    private fun onClickRemoveButton() {
        wishListController.removeItem(Item("", 100))
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
        wishListAdapter = WishListAdapter()
        binding.itemRecyclerview.apply {
            adapter = wishListAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun loadWishList() {
        lifecycleScope.launch {
            wishListController.getWishList()
        }
    }
}