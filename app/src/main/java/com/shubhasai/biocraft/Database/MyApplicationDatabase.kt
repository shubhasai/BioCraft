package com.shubhasai.biocraft.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shubhasai.biocraft.Dao.AcheivementDao
import com.shubhasai.biocraft.Dao.EducationDao
import com.shubhasai.biocraft.Dao.ExtracurricularDao
import com.shubhasai.biocraft.Dao.LanguageDao
import com.shubhasai.biocraft.Dao.ProjectDao
import com.shubhasai.biocraft.Dao.SkillsDao
import com.shubhasai.biocraft.Dao.UserDao
import com.shubhasai.biocraft.Dao.WorkExperienceDao
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.achievements
import com.shubhasai.biocraft.models.extracurricular
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.projects
import com.shubhasai.biocraft.models.skills
import com.shubhasai.biocraft.models.userprofile
import com.shubhasai.biocraft.models.workexperience

@Database(entities = [userprofile::class, Education::class,achievements::class,extracurricular::class, language::class,projects::class,skills::class,workexperience::class], version = 1)
abstract class MyApplicationDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun educationDao(): EducationDao
    abstract fun expDao(): WorkExperienceDao
    abstract fun projectDao(): ProjectDao
    abstract fun languageDao(): LanguageDao
    abstract fun skillsDao():SkillsDao
    abstract fun extractivityDao(): ExtracurricularDao
    abstract fun acheivementsDao(): AcheivementDao
    companion object {
        @Volatile
        private var INSTANCE: MyApplicationDatabase? = null

        fun getDatabase(context: Context): MyApplicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyApplicationDatabase::class.java,
                    "my_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

