package com.sheridan.stn991602827.fishingregulation.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FishingRegulationDataDao {
    @Query("SELECT * FROM regulations WHERE zoneId = :zoneId ORDER BY specie")
    fun getSpecifiedZoneRegulations(zoneId:Int): Flow<List<FishingRegulation>>

    @Query("SELECT * FROM regulations WHERE zoneId = :zoneId AND specie =:specie ORDER BY specie")
    fun getSpecifiedZoneAndSpecieRegulation(zoneId: Int,specie:String):Flow<FishingRegulation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fishingRegulations:List<FishingRegulation>)

    @Query("DELETE FROM regulations")
    suspend fun deleteAll()
}