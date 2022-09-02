package com.emonics.fooddelivery.Database

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

    @Query("SELECT id FROM user_table WHERE username= :username")
    fun getUserId(username: String): Int

    @Query("SELECT * from user_table where id= :id")
    fun getUser(id: Int): User

    @Query("Update user_table SET mobile= :mobile, address= :address WHERE id= :id")
    fun updateUser(mobile: Long, address: String, id: Int)

    @Insert
    fun insertUser(user: User)
}