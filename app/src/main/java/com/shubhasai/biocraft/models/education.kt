package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education")
class Education(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameOfTheInstitute: String = "",
    var degree: String = "",
    var specialisation: String = "",
    var startMonth: String = "",
    var endMonth: String = "",
    var startYear: String = "",
    var endYear: String = "",
    var grade: Double = 0.0
)
