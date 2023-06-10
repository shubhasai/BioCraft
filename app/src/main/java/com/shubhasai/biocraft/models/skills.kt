package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("skills")
class skills(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val nameOfTheSkill:String = "",
    val rating:Float = 5f,
)
