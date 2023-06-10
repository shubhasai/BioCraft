package com.shubhasai.biocraft.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubhasai.biocraft.models.projects
import com.shubhasai.biocraft.models.workexperience

@Dao
interface ProjectDao {
    @Insert
    suspend fun addProject(project: projects): Long

    @Update
    suspend fun updateProject(project: projects)

    @Delete
    suspend fun deleteProject(project: projects)

    @Query("SELECT * FROM projects WHERE id = :projectId")
    fun getProject(projectId: Int): LiveData<projects?>
    @Query("SELECT * FROM projects")
    fun getAllProject(): LiveData<List<projects>?>
}