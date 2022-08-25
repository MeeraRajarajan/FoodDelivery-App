package com.emonics.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturant)


        val btnchinese = findViewById<Button>(R.id.btn_chinese)
        val btnamerican = findViewById<Button>(R.id.btn_american)
       val btnthai= findViewById<Button>(R.id.btn_thai)

   btnamerican.setOnClickListener {
       val bundle =  Bundle()
       bundle.putString("data", "American")
       intent = Intent(this@RestaurantActivity, MenuActivity::class.java)
       intent.putExtras(bundle)
       startActivity(intent)
       }
        btnchinese.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("data", "Chinese")
            // intent.putExtra("data", "American")
            intent = Intent(this@RestaurantActivity, MenuActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
        btnthai.setOnClickListener {
            val bundle = Bundle()
            intent.putExtra("data", "Thai")
            intent = Intent(this@RestaurantActivity, MenuActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}