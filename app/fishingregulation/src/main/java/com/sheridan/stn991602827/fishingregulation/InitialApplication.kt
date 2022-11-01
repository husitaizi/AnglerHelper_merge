package com.sheridan.stn991602827.fishingregulation

import android.app.Application
import com.sheridan.stn991602827.fishingregulation.data.AppDatabase
import com.sheridan.stn991602827.fishingregulation.data.FishingRegulationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class InitialApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val repository by lazy { FishingRegulationRepository(database.fishingDao()) }
}