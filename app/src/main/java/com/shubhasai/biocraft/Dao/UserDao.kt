package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.userprofile

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo LIMIT 1")
    fun getUser(): LiveData<userprofile?>
    @Insert
    suspend fun insertUser(user: userprofile)
    @Update
    suspend fun updateUser(user: userprofile): Int
}