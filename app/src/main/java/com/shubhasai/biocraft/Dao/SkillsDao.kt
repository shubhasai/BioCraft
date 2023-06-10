package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.achievements
import com.shubhasai.biocraft.models.skills

@Dao
interface SkillsDao {
    @Insert
    suspend fun addSkill(skill: skills): Long

    @Update
    suspend fun updateSkill(ach: skills)

    @Delete
    suspend fun deleteSkill(ach: skills)

    @Query("SELECT * FROM skills WHERE id = :skillId")
    fun getSkill(skillId: Int): LiveData<skills?>
    @Query("SELECT * FROM skills")
    fun getAllskills(): LiveData<List<skills>?>
}