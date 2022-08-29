package com.emonics.fooddelivery


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuCustomAdapter(private val mList: List<MenuItemsViewModel>, private val viewModel: MenuActivityViewModel?) : RecyclerView.Adapter<MenuCustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menucard_view_design, parent, false)
        //  viewModel = View


        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val MenuItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(MenuItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = MenuItemsViewModel.text
        holder.tvdescription.text = MenuItemsViewModel.description.toString()
        holder.tvprice.text = "$"+MenuItemsViewModel.Price.toString()

        holder.acbutton.setOnClickListener {
            var curVal = 0
            if(holder.actextView.text.trim().length != 0){
                curVal = holder.actextView.text.toString().toInt()
            }
            mList[position].Qty = MenuItemsViewModel.Qty + 1
            holder.actextView.text = viewModel?.updateCount(curVal).toString()
        }

        holder.acbutton1.setOnClickListener {
            var curVal = 0
            if(holder.actextView.text.isNotEmpty()) {
                curVal = holder.actextView.text.toString().toInt()
            }
            mList[position].Qty =  mList[position].Qty - 1
            holder.actextView.text =  viewModel?.removeCount(curVal).toString()

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // return the  items in the list
    fun getItems(): List<MenuItemsViewModel> {
        return mList
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.tvname)
        val tvdescription: TextView = itemView.findViewById(R.id.tvdes)
        val tvprice : TextView = itemView.findViewById(R.id.tvprice)

        val actextView: TextView = itemView.findViewById(R.id.tvcount)
        val acbutton:Button = itemView.findViewById(R.id.btnadd)
        val acbutton1:Button = itemView.findViewById(R.id.btnremove)


    }
}