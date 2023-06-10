package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shubhasai.biocraft.models.Education

@Dao
interface EducationDao {
    @Insert
    suspend fun addEducation(education: Education): Long

    @Update
    suspend fun updateEducation(education: Education)

    @Delete
    suspend fun deleteEducation(education: Education)

    @Query("SELECT * FROM education WHERE id = :educationId")
    fun getEducation(educationId: Int): LiveData<Education?>
    @Query("SELECT * FROM education")
    fun getAllEducation(): LiveData<List<Education>?>
}
