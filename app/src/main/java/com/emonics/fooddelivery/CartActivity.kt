package com.emonics.fooddelivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var viewModel : MenuActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        viewModel = ViewModelProvider(this).get(MenuActivityViewModel::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.cartrecyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        var cartdata = intent.getParcelableArrayListExtra<MenuItemsViewModel>("cartinfo") as ArrayList<MenuItemsViewModel>
        val data = ArrayList<MenuItemsViewModel>()
        cartdata.removeAll{it.Qty == 0}

            val adapter = CartCustomAdapter( cartdata , viewModel)
            recyclerview.adapter = adapter


        }
}