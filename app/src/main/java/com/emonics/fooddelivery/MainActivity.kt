package com.emonics.fooddelivery

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var builder: AlertDialog.Builder? = null
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ConnectionManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val btn = findViewById<View>(R.id.btnGuest) as Button
        val button = findViewById<View>(R.id.btnSignup) as Button
        val textview = findViewById<TextView>(R.id.internetchk)
        val imageView = findViewById<ImageView>(R.id.internetimage)
        val networkInfo = ConnectionManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            imageView.visibility=INVISIBLE
            textview.visibility= INVISIBLE

        btn?.setOnClickListener()
        {
            val toast = Toast.makeText(applicationContext, "Continue as Guest", Toast.LENGTH_SHORT)
            toast.show()

            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
        button?.setOnClickListener()
        {
            val toast = Toast.makeText(applicationContext, "SignUp", Toast.LENGTH_SHORT)
            toast.show()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        }
        else {
          btn.visibility=INVISIBLE
            button.visibility= INVISIBLE
            textview.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
}
}
