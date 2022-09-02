package com.emonics.fooddelivery.Activity

import android.content.Intent
import com.emonics.fooddelivery.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.emonics.fooddelivery.Database.AppDatabase
import com.emonics.fooddelivery.DrawerBaseActivity

import com.emonics.fooddelivery.ItemsViewModel
import com.emonics.fooddelivery.callback.OnClickHomeItemListner
import com.emonics.fooddelivery.databinding.ActivityResturantBinding
import com.emonics.fooddelivery.fragments.HomeFragment
import com.emonics.fooddelivery.fragments.Restaurants_American

class RestaurantActivity :AppCompatActivity(), OnClickHomeItemListner {
    var homeFlag: Boolean = true
  // lateinit var activityResturantBinding: ActivityResturantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      /*  activityResturantBinding = ActivityResturantBinding.inflate(layoutInflater)
       setContentView(activityResturantBinding.root)
       navView.menu.findItem(R.id.nav_home).isChecked = true
       navView.menu.findItem(R.id.nav_home).isCheckable = true */

       setContentView(R.layout.activity_resturant)
        showHomeFramgment()
    }
    private fun showHomeFramgment() {
        val fragment = HomeFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitAllowingStateLoss()

        homeFlag = true
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showAmericanFragment() {
        val fragment = Restaurants_American.newInstance(1, R.raw.american)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitAllowingStateLoss()

        homeFlag = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun showChineseFragment() {
        val fragment = Restaurants_American.newInstance(1, R.raw.chinese)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitAllowingStateLoss()

        homeFlag = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showThaiFragment() {
        val fragment = Restaurants_American.newInstance(1, R.raw.thai)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitAllowingStateLoss()

        homeFlag = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClickHomeItem(item: ItemsViewModel) {
        when(item.text) {
            "American Food" -> {
                showAmericanFragment()
            }

            "Chinese Food" -> {
                showChineseFragment()
            }

            "Thai Food" -> {
                showThaiFragment()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                showHomeFramgment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

  override fun onBackPressed() {
        if( homeFlag == false )
        {
            showHomeFramgment()
            return;
        }
        super.onBackPressed()
    }

 /* override fun onBackPressed() {
      if (AppDatabase.getUserId() != 0) {
          val a = Intent(Intent.ACTION_MAIN)
          a.addCategory(Intent.CATEGORY_HOME)
          a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          startActivity(a)
          return
      } else {
          finish()
      }
  }*/
}