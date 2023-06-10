package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("achievements")
class achievements (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameOfTheAcheivement:String = ""
)