package com.emonics.fooddelivery.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emonics.fooddelivery.Activity.CartActivity
import com.emonics.fooddelivery.ViewModel.MenuActivityViewModel
import com.emonics.fooddelivery.ViewModel.MenuItemsViewModel
import com.emonics.fooddelivery.R

class CartCustomAdapter(private val context: Context, private val cList: List<MenuItemsViewModel>, private val viewModel: MenuActivityViewModel) : RecyclerView.Adapter<CartCustomAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cartviewdesign, parent, false)


        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val CartViewModel = cList[position]
        holder.actextView.text = CartViewModel.Qty.toString()
        holder.textView.text = CartViewModel.text
        holder.tvprice.text = "$"+"${CartViewModel.Price * CartViewModel.Qty}"

        holder.acbutton.setOnClickListener {

            var curVal = 0
            if (holder.actextView.text.trim().length != 0) {
                curVal = holder.actextView.text.toString().toInt()
            }
            cList[position].Qty = cList[position].Qty + 1
            holder.actextView.text = viewModel.updateCount(curVal).toString()
            holder.tvprice.text = "$"+"${cList[position].Price * cList[position].Qty}"
            (context as CartActivity).updateTotalPrice(calculateTotalPrice())
        }

        holder.acbutton1.setOnClickListener {
            var curVal = 0
            if (holder.actextView.text.trim().length !=0) {
                curVal = holder.actextView.text.toString().toInt()
            }
            if(cList[position].Qty != 0)
                cList[position].Qty = cList[position].Qty.minus(1)

            holder.actextView.text = viewModel.removeCount(curVal).toString()
            holder.tvprice.text = "$"+"${cList[position].Price * cList[position].Qty}"

            (context as CartActivity).updateTotalPrice(calculateTotalPrice())
        }
    }

    fun calculateTotalPrice(): Double
    {
        var totalprice :Double= 0.0
        cList.forEach {
            var price = it.Price
            var qty = it.Qty
            var tp = price * qty
            totalprice += tp
        }
        return totalprice
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return cList.size
    }

    fun getItems(): List<MenuItemsViewModel> {
        return cList
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.tvname)
        val tvprice: TextView = itemView.findViewById(R.id.tvprice)
        val actextView: TextView = itemView.findViewById(R.id.tvcount)
        val acbutton: Button = itemView.findViewById(R.id.btnadd)
        val acbutton1: Button = itemView.findViewById(R.id.btnremove)

    }
}