package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("projects")
class projects(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameOfTheProject:String = "",
    var description:String = "",
    var link:String = "",
    var learned:String = ""
)
