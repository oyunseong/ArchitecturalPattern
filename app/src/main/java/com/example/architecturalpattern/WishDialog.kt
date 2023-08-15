package com.example.architecturalpattern

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturalpattern.databinding.ActivityMainBinding
import com.example.architecturalpattern.model.Item
import com.example.architecturalpattern.model.WishListRepository

class WishDialog(context: Context) : DialogFragment(), WishView {
    private val wishListController: WishListController = WishListController(
        wishListRepository = WishListRepository(), wishView = this
    )
    private lateinit var binding: ActivityMainBinding

    private lateinit var wishListAdapter: WishListAdapter
    private lateinit var wishRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initializeWishListRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun notifyWishListChanged(wishList: List<Item>) {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressbar.visibility = View.GONE
    }


    private fun initializeWishListRecyclerView() {
        wishListAdapter = WishListAdapter()
        wishRecyclerView.apply {
            adapter = wishListAdapter
            wishRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}