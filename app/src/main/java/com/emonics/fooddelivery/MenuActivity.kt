package com.emonics.fooddelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
   val imageView = findViewById<ImageView>(R.id.imageview1)
                        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val chinesedata = ArrayList<ItemsViewModel>()
        chinesedata.add(ItemsViewModel(R.drawable.chow_mein, " Chow Mein"," consists of noodles, meat (usually chicken, beef, shrimp, or pork), onions, and celery.",13.99))
        chinesedata.add(ItemsViewModel(R.drawable.dim_sum, "Dim sum","It contains a large range of small dishes, including dumplings, rolls, cakes, and meat, seafood, dessert, and vegetable preparations.",15.99))
        chinesedata.add(ItemsViewModel(R.drawable.dumplings, "Dumplings","consist of minced meat and/or chopped vegetables wrapped in a thin dough skin.",16.78))
        chinesedata.add(ItemsViewModel(R.drawable.kung_pao_chicken, "Kung Pao Chicken","The major ingredients are diced chicken, dried chili, cucumber, and fried peanuts (or cashews).",18.88))
        chinesedata.add(ItemsViewModel(R.drawable.ma_po_tofu, "Ma Po Tofu","t consists of beancurd along with some minced meat  in a spicy sauce.",20.99))
        chinesedata.add(ItemsViewModel(R.drawable.peking_duck, "Peking Roasted Duck","is often eaten with pancakes, sweet bean sauce, or soy sauce with mashed garlic.",15.99))

        val americandata = ArrayList<ItemsViewModel>()
        americandata.add(ItemsViewModel(R.drawable.applepie, "Apple Pie","Apple pie is often served with whipped cream, ice cream, or cheddar cheese.",20.99))
        americandata.add(ItemsViewModel(R.drawable.cheeseburger, "Cheese Burgers","Cheeseburger is a hamburger topped with cheese and slice of cheese.",15.89))
        americandata.add(ItemsViewModel(R.drawable.images, "Pizza","Pizza is flat base of leavened wheat-based dough topped ingredients.", 13.99))
        americandata.add(ItemsViewModel(R.drawable.hotchicken, "Hot Chicken","Hot Chicken is a deep-fried chicken, slathered in a spicy hot paste.",6.99))
        americandata.add(ItemsViewModel(R.drawable.donut, "Doughnut","Donut is a type of food made from leavened fried dough.",14.89))
        americandata.add(ItemsViewModel(R.drawable.waffle, "waffle","Waffle is a dish made from leavened batter or dough  between two plates.",10.99))


        val thaidata = ArrayList<ItemsViewModel>()
        thaidata.add(ItemsViewModel(R.drawable.noodle_soup, "Noodle Soup", "A type of noodle soup that can be made with chicken, pork, or beef as well as either rice noodles or egg noodles.",15.67))
        thaidata.add(ItemsViewModel(R.drawable.spicy_shrimp_soup, "Spicy Shrimp Soup", " A soup created with Thai ingredients like lemongrass, chilli, galangal, kaffier lime leaves, shallots, fresh lime juice and fish sauce.",19.76))
        thaidata.add(ItemsViewModel(R.drawable.chicken_coconut_soup, "Chicken in Coconut Soup", "This soup includes fiery chilies, thinly sliced young galangal, crushed shallots, stalks of lemongrass and tender strips of chicken and comes with lots of creamy coconut milk.",13.99))
        thaidata.add(ItemsViewModel(R.drawable.spicy_papaya_salad, "Spicy Green Papaya Salad", "Consists of shredded green papaya, tomatoes, carrots, peanuts, dried shrimp, runner beans, palm sugar, tamarind pulp, fish sauce, lime juice, garlic and chillies.",20.99))
        thaidata.add(ItemsViewModel(R.drawable.fried_noodles, "Thai Style Fried Noodles", "A fried noodle dish which is usually made with shrimp or chicken with a vegetarian option.",16.99))
        thaidata.add(ItemsViewModel(R.drawable.fried_rice, "Fried Rice", "Popular dish consisting of fried rice, egg, onion and a few herbs.",18.79))

        // Setting the Adapter with the recyclerview

        val bundle = intent.extras
        var adapter: CustomAdapter? =null
// performing the safety null check
        var s:String? = bundle!!.getString("data")

        // getting the string back
        adapter = if(s.toString()== "American"){
             imageView.setImageResource(R.drawable.american)
            CustomAdapter(americandata)
        } else if(s.toString()== "Chinese"){
            imageView.setImageResource(R.drawable.ch_1)
            CustomAdapter(chinesedata)
        } else{
              imageView.setImageResource(R.drawable.chicken_noodle_soup)
            CustomAdapter(thaidata)
        }

        recyclerview.adapter = adapter
    }
}