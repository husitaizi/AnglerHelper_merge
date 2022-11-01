package com.sheridan.stn991602827.fishingregulation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData


class FishingRegulationViewModel(private val repository: FishingRegulationRepository) :
    ViewModel() {

//    fun allZoneRegulations(zoneId: Int) :LiveData<List<FishingRegulation>>{
//      return  repository.getSpecifiedZoneRegulations(zoneId).asLiveData()
//    }

    fun allZoneRegulations(zoneId: Int =1) :LiveData<List<FishingRegulation>>{
        return  repository.getSpecifiedZoneRegulations(zoneId).asLiveData()
    }


    fun getRegulationsOnZoneAndSpiece(zoneId: Int, spiece: String) {
        repository.getSpecifiedZoneAndSpecieRegulation(zoneId, spiece)
    }


/*
    class FishingRegulationViewModelFactory(private val repository: FishingRegulationRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            //TODO("Not yet implemented")
            if (modelClass.isAssignableFrom(FishingRegulationViewModel::class.java)) {
                return FishingRegulationViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

 */
}