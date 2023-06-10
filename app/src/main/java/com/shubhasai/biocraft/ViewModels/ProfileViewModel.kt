package com.shubhasai.biocraft.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shubhasai.biocraft.Dao.UserDao
import com.shubhasai.biocraft.Database.MyApplicationDatabase
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.achievements
import com.shubhasai.biocraft.models.extracurricular
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.projects
import com.shubhasai.biocraft.models.skills
import com.shubhasai.biocraft.models.userprofile
import com.shubhasai.biocraft.models.workexperience
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val educationDao = MyApplicationDatabase.getDatabase(application).educationDao()
    private val expDao = MyApplicationDatabase.getDatabase(application).expDao()
    private val projectDao = MyApplicationDatabase.getDatabase(application).projectDao()
    private val languageDao = MyApplicationDatabase.getDatabase(application).languageDao()
    private val userDao: UserDao by lazy {
        MyApplicationDatabase.getDatabase(getApplication()).userDao()
    }
    fun getUser(): LiveData<userprofile?> {
        return userDao.getUser()
    }
    fun insertUser(user: userprofile) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }
    fun updateUser(user: userprofile): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            val rowsUpdated = userDao.updateUser(user)
            result.postValue(rowsUpdated)
        }
        return result
    }
    // Education methods
    fun addEducation(education: Education) {
        viewModelScope.launch {
            educationDao.addEducation(education)
        }
    }

    fun updateEducation(education: Education) = viewModelScope.launch {
        educationDao.updateEducation(education)
    }

    fun deleteEducation(education: Education) = viewModelScope.launch {
        educationDao.deleteEducation(education)
    }

    suspend fun getEducation(educationId: Int): LiveData<Education?> {
        return educationDao.getEducation(educationId)
    }
    fun getallEducation(): LiveData<List<Education>?> {
        return educationDao.getAllEducation()
    }
    // Exp methods
    fun addExp(exp: workexperience) {
        viewModelScope.launch {
            expDao.addExp(exp)
        }
    }

    fun updateExp(exp: workexperience) = viewModelScope.launch {
        expDao.updateExp(exp)
    }

    fun deleteExp(exp: workexperience) = viewModelScope.launch {
        expDao.deleteExp(exp)
    }

    suspend fun getExp(expid: Int): LiveData<workexperience?> {
        return expDao.getExp(expid)
    }
    fun getallExp(): LiveData<List<workexperience>?> {
        return expDao.getAllExp()
    }
    // Projects
    fun addProject(project: projects) {
        viewModelScope.launch {
            projectDao.addProject(project)
        }
    }

    fun updateProject(project: projects) = viewModelScope.launch {
        projectDao.updateProject(project)
    }

    fun deleteProject(project: projects) = viewModelScope.launch {
        projectDao.deleteProject(project)
    }

    suspend fun getProject(project: Int): LiveData<projects?> {
        return projectDao.getProject(project)
    }
    fun getallProject(): LiveData<List<projects>?> {
        return projectDao.getAllProject()
    }
    // Language
    fun addLang(lang: language) {
        viewModelScope.launch {
            languageDao.addLanguage(lang)
        }
    }

    fun updateLang(lang: language) = viewModelScope.launch {
        languageDao.updateLanguage(lang)
    }

    fun deleteLang(lang: language) = viewModelScope.launch {
        languageDao.deleteLanguage(lang)
    }

    suspend fun getLang(lang: Int): LiveData<language?> {
        return languageDao.getLanguage(lang)
    }
    fun getallLangs(): LiveData<List<language>?> {
        return languageDao.getAllLanguage()
    }

    // Skills
    private val skillsDao = MyApplicationDatabase.getDatabase(application).skillsDao()
    fun addSkill(skills: skills) {
        viewModelScope.launch {
            skillsDao.addSkill(skills)
        }
    }

    fun updateSKill(skills: skills) = viewModelScope.launch {
        skillsDao.updateSkill(skills)
    }

    fun deleteSkill(skills: skills) = viewModelScope.launch {
        skillsDao.deleteSkill(skills)
    }

    suspend fun getSkill(skills: Int): LiveData<skills?> {
        return skillsDao.getSkill(skills)
    }
    fun getallSkill(): LiveData<List<skills>?> {
        return skillsDao.getAllskills()
    }

    //Extracurricular Activity
    private val extracurricularDao = MyApplicationDatabase.getDatabase(application).extractivityDao()
    fun addActivity(extracurricular: extracurricular) {
        viewModelScope.launch {
            extracurricularDao.addExt(extracurricular)
        }
    }

    fun updateActivity(extracurricular: extracurricular) = viewModelScope.launch {
        extracurricularDao.updateExt(extracurricular)
    }

    fun deleteActivity(extracurricular: extracurricular) = viewModelScope.launch {
        extracurricularDao.deleteExt(extracurricular)
    }

    suspend fun getActivity(activity: Int): LiveData<extracurricular?> {
        return extracurricularDao.getExt(activity)
    }
    fun getallActivity(): LiveData<List<extracurricular>?> {
        return extracurricularDao.getAllExt()
    }

    // Acheivements
    private val achievementDao = MyApplicationDatabase.getDatabase(application).acheivementsDao()
    fun addAchievement(achievement:achievements) {
        viewModelScope.launch {
            achievementDao.addAcheivement(achievement)
        }
    }

    fun updateAchievement(achievement: achievements) = viewModelScope.launch {
        achievementDao.updateAcheivement(achievement)
    }

    fun deleteAchievement(achievement: achievements) = viewModelScope.launch {
        achievementDao.deleteAcheivement(achievement)
    }

    suspend fun getAchievemnet(achievement: Int): LiveData<achievements?> {
        return achievementDao.getAcheivement(achievement)
    }
    fun getallAchievement(): LiveData<List<achievements>?> {
        return achievementDao.getAllAcheivements()
    }
}
