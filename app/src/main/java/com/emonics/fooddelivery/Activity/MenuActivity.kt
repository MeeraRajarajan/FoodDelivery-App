package com.emonics.fooddelivery.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emonics.fooddelivery.Adapter.MenuCustomAdapter
import com.emonics.fooddelivery.Database.AppDatabase
import com.emonics.fooddelivery.DrawerBaseActivity
import com.emonics.fooddelivery.R
import com.emonics.fooddelivery.ViewModel.MenuActivityViewModel
import com.emonics.fooddelivery.ViewModel.MenuItemsViewModel
import com.emonics.fooddelivery.databinding.ActivityMenuBinding


class MenuActivity :  DrawerBaseActivity() {
    private lateinit var viewModel : MenuActivityViewModel

    lateinit var activityMenuBinding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMenuBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(activityMenuBinding.root)
        navView.menu.findItem(R.id.nav_home).isChecked = true
        navView.menu.findItem(R.id.nav_home).isCheckable = true


        viewModel = ViewModelProvider(this).get(MenuActivityViewModel::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        val imageView = findViewById<ImageView>(R.id.imageview1)
        // this creates a vertical layout Manager

        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val americandata = ArrayList<MenuItemsViewModel>()
        americandata.add(MenuItemsViewModel(R.drawable.applepie, "Apple pie","Apple pie is often served with whipped cream, ice cream, or cheddar cheese.",20.99,0))
        americandata.add(MenuItemsViewModel(R.drawable.cheeseburger, "Cheeseburgers","Cheeseburger is a hamburger topped with cheese and slice of cheese.",15.89,0))
        americandata.add(MenuItemsViewModel(R.drawable.images, "Pizza","Pizza is flat base of leavened wheat-based dough topped ingredients.", 13.99,0))
        americandata.add(MenuItemsViewModel(R.drawable.hotchicken, "Hot Chicken","Hot Chicken is a deep-fried chicken, slathered in a spicy hot paste.",6.99,0))
        americandata.add(MenuItemsViewModel(R.drawable.donut, "Doughnut","Donut is a type of food made from leavened fried dough.",14.89,0))
        americandata.add(MenuItemsViewModel(R.drawable.waffle, "waffle","Waffle is a dish made from leavened batter or dough  between two plates.",10.99,0))

        val chinesedata = ArrayList<MenuItemsViewModel>()
        chinesedata.add(MenuItemsViewModel(R.drawable.chow_mein, " Chow Mein", " consists of noodles, meat (usually chicken, beef, shrimp, or pork), onions, and celery.", 13.99,0))
        chinesedata.add(MenuItemsViewModel(R.drawable.dim_sum, "Dim sum", "It contains a large range of small dishes, including dumplings, rolls, cakes, and meat, seafood, dessert, and vegetable preparations.", 15.99,0))
        chinesedata.add(MenuItemsViewModel(R.drawable.dumplings, "Dumplings", "consist of minced meat and/or chopped vegetables wrapped in a thin dough skin.", 16.78,0))
        chinesedata.add(MenuItemsViewModel(R.drawable.kung_pao_chicken, "Kung Pao Chicken", "The major ingredients are diced chicken, dried chili, cucumber, and fried peanuts (or cashews).", 18.88,0))
        chinesedata.add(MenuItemsViewModel(R.drawable.ma_po_tofu, "Ma Po Tofu", "t consists of beancurd along with some minced meat  in a spicy sauce.", 20.99,0))
        chinesedata.add(MenuItemsViewModel(R.drawable.peking_duck, "Peking Roasted Duck", "is often eaten with pancakes, sweet bean sauce, or soy sauce with mashed garlic.",15.99,0))
        // This will pass the ArrayList to our Adapter

        val thaidata = ArrayList<MenuItemsViewModel>()
        thaidata.add(MenuItemsViewModel(R.drawable.noodle_soup, "Noodle Soup", "A type of noodle soup that can be made with chicken, pork, or beef as well as either rice noodles or egg noodles.", 15.67,0))
        thaidata.add(MenuItemsViewModel(R.drawable.spicy_shrimp_soup, "Spicy Shrimp Soup", " A soup created with Thai ingredients like lemongrass, chilli, galangal, kaffier lime leaves, shallots, fresh lime juice and fish sauce.", 19.76,0))
        thaidata.add(MenuItemsViewModel(R.drawable.chicken_coconut_soup, "Chicken in Coconut Soup", "This soup includes fiery chilies, thinly sliced young galangal, crushed shallots, stalks of lemongrass and tender strips of chicken and comes with lots of creamy coconut milk.", 13.99,0))
        thaidata.add(MenuItemsViewModel(R.drawable.spicy_papaya_salad, "Spicy Green Papaya Salad", "Consists of shredded green papaya, tomatoes, carrots, peanuts, dried shrimp, runner beans, palm sugar, tamarind pulp, fish sauce, lime juice, garlic and chillies.", 20.99,0))
        thaidata.add(MenuItemsViewModel(R.drawable.fried_noodles, "Thai Style Fried Noodles", "A fried noodle dish which is usually made with shrimp or chicken with a vegetarian option.", 16.99,0))
        thaidata.add(MenuItemsViewModel(R.drawable.fried_rice, "Fried Rice", "Popular dish consisting of fried rice, egg, onion and a few herbs.", 18.79,0))

        val bundle = intent.extras
        var adapter: MenuCustomAdapter? = null
// performing the safety null check
        var s: String? = bundle!!.getString("data")

        // getting the string back
        if (s.toString() == "American") {
            imageView.setImageResource(R.drawable.american)
            adapter = MenuCustomAdapter(americandata, viewModel)
        } else if (s.toString() == "Chinese") {
            imageView.setImageResource(R.drawable.ch_1)
            adapter = MenuCustomAdapter(chinesedata,viewModel)
        } else {
            imageView.setImageResource(R.drawable.chicken_noodle_soup)
            adapter = MenuCustomAdapter(thaidata,viewModel)
        }

        recyclerview.adapter = adapter

        val btn = findViewById<Button>(R.id.btncart)
        btn.setOnClickListener {
            var d = (recyclerview.getAdapter() as MenuCustomAdapter).getItems()
            val intent = Intent(this@MenuActivity, CartActivity::class.java)
            intent.putExtra("cartinfo", d as java.util.ArrayList<out Parcelable>)
            startActivity(intent)
        }
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
