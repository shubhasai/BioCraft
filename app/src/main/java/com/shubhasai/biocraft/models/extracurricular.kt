package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("extracurricular")
 class extracurricular(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val nameOfTheActivity:String = "",
    val description:String = ""
)
