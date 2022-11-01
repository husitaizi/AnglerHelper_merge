package com.sheridan.stn991602827.fishingregulation.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FishingRegulationRepository @Inject constructor(
    private val fishingRegulationDataDao:
    FishingRegulationDataDao
) {

    fun getSpecifiedZoneRegulations(zoneId: Int) =
        fishingRegulationDataDao.getSpecifiedZoneRegulations(zoneId)


    fun getSpecifiedZoneAndSpecieRegulation(zoneId: Int, specie: String) =
        fishingRegulationDataDao.getSpecifiedZoneAndSpecieRegulation(zoneId, specie)

    companion object {
        // For singleton instantiation
        @Volatile
        private var instance: FishingRegulationRepository? = null

        fun getInstance(fishingRegulationDataDao: FishingRegulationDataDao) {
            instance ?: synchronized(this) {
                instance ?: FishingRegulationRepository(fishingRegulationDataDao).also {
                    instance = it
                }
            }
        }
    }
}