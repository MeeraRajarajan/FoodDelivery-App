package com.emonics.fooddelivery.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emonics.fooddelivery.Adapter.CartCustomAdapter
import com.emonics.fooddelivery.Database.AppDatabase
import com.emonics.fooddelivery.DrawerBaseActivity
import com.emonics.fooddelivery.PaymentActivity
import com.emonics.fooddelivery.R
import com.emonics.fooddelivery.ViewModel.MenuActivityViewModel
import com.emonics.fooddelivery.ViewModel.MenuItemsViewModel
import com.emonics.fooddelivery.adapters.CustomAdapter
import com.emonics.fooddelivery.databinding.ActivityCartBinding
import kotlinx.android.synthetic.main.activity_cart.*
import kotlin.math.round

class CartActivity :  DrawerBaseActivity() {
    lateinit var activityCartBinding: ActivityCartBinding

    private lateinit var viewModel : MenuActivityViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityCartBinding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(activityCartBinding.root)
        navView.menu.findItem(R.id.nav_home).isChecked = true
        navView.menu.findItem(R.id.nav_home).isCheckable = true

        viewModel = ViewModelProvider(this).get(MenuActivityViewModel::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.cartrecyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        var cartdata = intent.getParcelableArrayListExtra<MenuItemsViewModel>("cartinfo") as ArrayList<MenuItemsViewModel>
        val data = ArrayList<MenuItemsViewModel>()
        cartdata.removeAll{it.Qty == 0}

            val adapter = CartCustomAdapter( this@CartActivity,cartdata , viewModel)
            recyclerview.adapter = adapter


        var totalprice :Double= 0.0

        var c = (recyclerview.getAdapter() as CartCustomAdapter).getItems()

        c.forEach {
            var price = it.Price
            var qty = it.Qty
            var tp = price * qty
            totalprice += tp
        }
        //total.text = "Total Price : " + "$ " + round(totalprice.toString().toDouble())
        updateTotalPrice(totalprice)


        val btncheck= findViewById<Button>(R.id.btncheckout)
        btncheck.setOnClickListener(){

            val intent = Intent( this@CartActivity,PaymentActivity::class.java)
            intent.putExtra("Total Price", totalprice.toString())
            startActivity(intent)

        }
        }

    fun updateTotalPrice(value: Double)
    {
        val total = findViewById<TextView>(R.id.totalprice)
        total.text = "Total Price : " + "$ " + round(value.toString().toDouble())
    }

    override fun onBackPressed() {
        if (AppDatabase.getUserId() != 0) {
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(a)
            return
        } else {
            finish()
        }

    }


}
