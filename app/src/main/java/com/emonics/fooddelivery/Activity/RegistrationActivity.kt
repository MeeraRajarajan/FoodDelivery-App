package com.emonics.fooddelivery.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.emonics.fooddelivery.Database.AppDatabase
import com.emonics.fooddelivery.Database.User
import com.emonics.fooddelivery.DrawerBaseActivity
import com.emonics.fooddelivery.R
import com.emonics.fooddelivery.databinding.ActivityRegistrationBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class RegistrationActivity : DrawerBaseActivity() {
    lateinit var usernameTL: TextInputLayout
    lateinit var emailTL: TextInputLayout
    var usernameBool: Boolean = false
    var emailBool: Boolean = false

    private lateinit var appDb : AppDatabase
    lateinit var activityRegistrationBinding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(activityRegistrationBinding.root)
        navView.menu.findItem(R.id.nav_home).isChecked = true
        navView.menu.findItem(R.id.nav_home).isCheckable = true


        // initialize database
        appDb = AppDatabase.getDatabase(this@RegistrationActivity)

        val usernameET = findViewById<EditText>(R.id.usernameET)
        val passwordET = findViewById<EditText>(R.id.passwordET)
        val mobileET = findViewById<EditText>(R.id.mobileET)
        val emailET = findViewById<EditText>(R.id.emailET)
        val addressET = findViewById<EditText>(R.id.addressET)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        usernameTL = findViewById(R.id.usernameTL)
        emailTL = findViewById(R.id.emailTL)

        checkUsername(usernameET)
        checkEmail(emailET)

        registerBtn.setOnClickListener{

            val mobileBool = checkMobile(mobileET)
            val passwordBool = checkPassword(passwordET)
            val addressBool = checkAddress(addressET)

            if (usernameBool && emailBool && mobileBool && passwordBool && addressBool) {
                //write data to database
                val user = User(null, usernameET.text.toString(), emailET.text.toString(),
                    passwordET.text.toString(), mobileET.text.toString().toLong(),
                    addressET.text.toString())
                GlobalScope.launch(Dispatchers.IO) {
                    appDb.userDao().insertUser(user)
                }

                Toast.makeText(this, "REGISTERED", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RestaurantActivity::class.java)
                startActivity(intent)
                usernameET.text = null
                passwordET.text = null
                mobileET.text = null
                emailET.text = null
                addressET.text = null
            } else {
                if (!usernameBool) {
                    usernameTL.error = "Enter username!"
                }
                if (!emailBool) {
                    emailTL.error = "Enter email!"
                }
            }
        }
    }


    private fun checkUsername(u: EditText) {
        var timer = Timer()
        val delay: Long = 2000

        u.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer.cancel()
                timer.purge()
                runOnUiThread(object : Runnable {
                    override fun run() {
                        usernameTL.error = null
                        usernameTL.endIconDrawable = null
                    }

                })
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        GlobalScope.launch(Dispatchers.IO) {
                            val usernameList = appDb.userDao().getUsernames()
                            runOnUiThread {
                                if (usernameList.contains(u.text.toString())) {
                                    usernameTL.error = "Username taken!"
                                    usernameBool = false
                                } else {
                                    if (!TextUtils.isEmpty(u.text.toString())) {
                                        usernameTL.setEndIconDrawable(R.drawable.ic_check_mark)
                                        usernameBool = true
                                    }
                                }
                            }
                        }
                    }
                }, delay)
            }
        })
    }

    private fun checkPassword(p: EditText): Boolean {
        val passwordTL = findViewById<TextInputLayout>(R.id.passwordTL)
        p.doOnTextChanged { _, _, _, _ ->
            passwordTL.error = null
        }
        return if(!TextUtils.isEmpty(p.text.toString())) {
            true
        } else {
            passwordTL.error = "Enter password!"
            false
        }
    }

    private fun checkMobile(m: EditText): Boolean {
        val mobileTL = findViewById<TextInputLayout>(R.id.mobileTL)
        return try {
            m.doOnTextChanged { _, _, _, _ ->
                mobileTL.error = null
            }
            m.text.toString().toLong()
            true
        } catch (e : Exception) {
            mobileTL.error = "Invalid mobile number!"
            false
        }
    }

    private fun checkEmail(e: EditText) {
        var timer = Timer()
        val delay: Long = 2000

        e.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer.cancel()
                timer.purge()
                runOnUiThread(object : Runnable {
                    override fun run() {
                        emailTL.error = null
                        emailTL.endIconDrawable = null
                    }
                })
            }

            override fun afterTextChanged(p0: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        GlobalScope.launch(Dispatchers.IO) {
                            val emailList = appDb.userDao().getEmails()

                            runOnUiThread {
                                val validEmail = android.util.Patterns
                                    .EMAIL_ADDRESS.matcher(e.text.toString()).matches()

                                if (validEmail) {
                                    if (emailList.contains(e.text.toString())) {
                                        emailTL.error = "Email already in use. Enter another."
                                        emailBool = false
                                    } else {
                                        if (!TextUtils.isEmpty(e.text.toString())) {
                                            emailTL.setEndIconDrawable(R.drawable.ic_check_mark)
                                            emailBool = true
                                        }
                                    }
                                } else {
                                    if (!TextUtils.isEmpty(e.text.toString())) {
                                        emailTL.error = "Invalid email!"
                                    }
                                }
                            }
                        }
                    }
                }, delay)
            }
        })
    }

    private fun checkAddress(a: EditText): Boolean {
        val addressTL = findViewById<TextInputLayout>(R.id.addressTL)
        a.doOnTextChanged { _, _, _, _ ->
            addressTL.error = null
        }
        return if (!TextUtils.isEmpty(a.text.toString())) {
            true
        } else {
            addressTL.error = "Enter address!"
            false
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