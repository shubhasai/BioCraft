package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.extracurricular

@Dao
interface ExtracurricularDao {
    @Insert
    suspend fun addExt(ext: extracurricular): Long

    @Update
    suspend fun updateExt(ext: extracurricular)

    @Delete
    suspend fun deleteExt(ext: extracurricular)

    @Query("SELECT * FROM extracurricular WHERE id = :extId")
    fun getExt(extId: Int): LiveData<extracurricular?>
    @Query("SELECT * FROM extracurricular")
    fun getAllExt(): LiveData<List<extracurricular>?>
}