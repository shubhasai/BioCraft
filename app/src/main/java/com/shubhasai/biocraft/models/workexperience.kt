package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workexp")
class workexperience(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameOfTheOrganisation:String = "",
    var position:String = "",
    var startMonth:String = "",
    var endMonth:String = "",
    var startYear:String = "",
    var endYear:String = "",
    var work:String = " ",
)
