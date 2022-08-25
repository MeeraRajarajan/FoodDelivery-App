package com.emonics.fooddelivery


import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT username FROM user_table")
    fun getUsernames(): List<String>

    @Query("SELECT email FROM user_table")
    fun getEmails(): List<String>

    @Query("SELECT password FROM user_table")
    fun getPassword(): List<String>


    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username= :username AND password= :password)")
    fun checkUsernameAndPassword(username: String, password: String): Boolean

    @Insert
    fun insertUser(user: User)
}