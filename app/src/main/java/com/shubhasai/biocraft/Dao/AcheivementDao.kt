package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.achievements

@Dao
interface AcheivementDao {
    @Insert
    suspend fun addAcheivement(ach: achievements): Long

    @Update
    suspend fun updateAcheivement(ach: achievements)

    @Delete
    suspend fun deleteAcheivement(ach: achievements)

    @Query("SELECT * FROM achievements WHERE id = :achId")
    fun getAcheivement(achId: Int): LiveData<achievements?>
    @Query("SELECT * FROM achievements")
    fun getAllAcheivements(): LiveData<List<achievements>?>
}