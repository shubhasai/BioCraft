package com.shubhasai.biocraft.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInfo")
class userprofile(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var linkedin: String = "",
    var github: String = "",
    var leetcode: String = "",
    var stackOverflow: String = "",
    var dribble: String = "",
    var facebook: String = "",
    var instagram: String = "",
    var youtube: String = "",
    var link1: String = "",
    var link2: String = "",
    var state: String = "",
    var dist: String = "",
    var locality: String = "",
    var pincode: String = ""
)

