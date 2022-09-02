package com.emonics.fooddelivery.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.emonics.fooddelivery.Database.AppDatabase
import com.emonics.fooddelivery.DrawerBaseActivity
import com.emonics.fooddelivery.R
import com.emonics.fooddelivery.databinding.ActivityLoginBinding
import com.emonics.fooddelivery.databinding.ActivityResturantBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class LoginActivity :  DrawerBaseActivity() {
    lateinit var activityLoginBinding: ActivityLoginBinding

    private lateinit var edt_userName: EditText
    private lateinit var edt_password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var btnCancel: Button

    private var strCheckUserName_Password by Delegates.notNull<Boolean>()

    // strCheckUserName_Password

    private lateinit var checkbox: CheckBox

    private lateinit var strCheckbox: String


    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_login)

        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
         navView.menu.findItem(R.id.nav_home).isChecked = true
          navView.menu.findItem(R.id.nav_home).isCheckable = true

        // initialize database
        appDb = AppDatabase.getDatabase(this@LoginActivity)

        edt_userName = findViewById(R.id.et_username)
        edt_password = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup)
        btnCancel = findViewById(R.id.btn_cancel)


        btnLogin.setOnClickListener {


            var str_userName_id = edt_userName.text.toString()
            var str_password = edt_password.text.toString()


            GlobalScope.launch(Dispatchers.IO) {

                var checkUsernamePW =
                    appDb.userDao().checkUsernameAndPassword(str_userName_id, str_password)
                val userId = appDb.userDao().getUserId(str_userName_id)
                AppDatabase.setUserId(userId)
                println("login Successful  " + checkUsernamePW)


                runOnUiThread {



                    if (checkUsernamePW) {
                        startActivity(Intent(this@LoginActivity, RestaurantActivity::class.java))
                        Toast.makeText(this@LoginActivity,"Successfully Log in ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@LoginActivity,"Invalid username or password ", Toast.LENGTH_SHORT).show()
                        //startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
                        edt_userName.setText("")
                        edt_password.setText("")
                    }
                }
            }
        }


        btnCancel.setOnClickListener {

            edt_userName.setText("")
            edt_password.setText("")
        }

        btnSignup.setOnClickListener {

            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
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

