package com.emonics.fooddelivery

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

class CartActivity : AppCompatActivity() {

    private lateinit var viewModel : MenuActivityViewModel

    @SuppressLint("SetTextI18n")
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

        val total = findViewById<TextView>(R.id.totalprice)
        var totalprice :Double= 0.0

        var c = (recyclerview.getAdapter() as CartCustomAdapter).getItems()

        c.forEach {
            var price = it.Price
            var qty = it.Qty
            var tp = price * qty
            totalprice += tp
        }
        total.text = "Total Price : " + "$ " + round(totalprice.toString().toDouble())
        }
}