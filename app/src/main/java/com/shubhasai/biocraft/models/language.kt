package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("language")
class language(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nameOfTheLanguage:String = "",
)
