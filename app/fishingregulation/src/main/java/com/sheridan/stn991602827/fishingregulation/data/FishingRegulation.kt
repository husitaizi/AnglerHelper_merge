package com.sheridan.stn991602827.fishingregulation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regulations")
data class FishingRegulation (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val zoneId:Int,
    val specie:String,
    val limitS:String,
    val limitC:String,
    val size:String,
    val openSeason:String,
    val closeSeason:String
        )