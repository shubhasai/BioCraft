package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.projects

@Dao
interface LanguageDao {
    @Insert
    suspend fun addLanguage(lang: language): Long

    @Update
    suspend fun updateLanguage(lang: language)

    @Delete
    suspend fun deleteLanguage(lang: language)

    @Query("SELECT * FROM language WHERE id = :languageId")
    fun getLanguage(languageId: Int): LiveData<language?>
    @Query("SELECT * FROM language")
    fun getAllLanguage(): LiveData<List<language>?>
}