package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.workexperience

@Dao
interface WorkExperienceDao {
    @Insert
    suspend fun addExp(education: workexperience): Long

    @Update
    suspend fun updateExp(education: workexperience)

    @Delete
    suspend fun deleteExp(education: workexperience)

    @Query("SELECT * FROM workexp WHERE id = :expId")
    fun getExp(expId: Int): LiveData<workexperience?>
    @Query("SELECT * FROM workexp")
    fun getAllExp(): LiveData<List<workexperience>?>
}