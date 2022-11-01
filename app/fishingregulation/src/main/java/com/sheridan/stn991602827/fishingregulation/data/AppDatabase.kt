package com.sheridan.stn991602827.fishingregulation.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sheridan.stn991602827.fishingregulation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader


@Database(entities = [FishingRegulation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fishingDao(): FishingRegulationDataDao

    private class AppdatabaseCallback(
        private val context: Context,
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var fishingDao = database.fishingDao()

                    fishingDao.deleteAll()

                    // prepopulate the database

                    prepopulateData(context, fishingDao)
                }

            }
        }

        private suspend fun prepopulateData(
            context: Context,
            fishingDao: FishingRegulationDataDao
        ) {
            //   TODO("Not yet implemented")


            var fishList: MutableList<FishingRegulation> = mutableListOf()

            try {

                val fishingRegulations = loadJSONArray(context)
                if (fishingRegulations != null) {
                    for (i in 0 until fishingRegulations.length()) {
                        val item = fishingRegulations.getJSONObject(i)
                        val zoneId = item.getInt("zoneId")
                        val specie = item.getString("specie")
                        val limitS = item.getString("limitS")
                        val limitC = item.getString("limitC")
                        val size = item.getString("size")
                        val openSeason = item.getString("openSeason")
                        val closeSeason = item.getString("closeSeason")

                        val fishingRegulationEntity = FishingRegulation(
                            0,
                            zoneId,
                            specie,
                            limitS,
                            limitC,
                            size,
                            openSeason,
                            closeSeason
                        )
                        fishList?.add(fishingRegulationEntity)
                    } // end of for
                    fishingDao?.insertAll(fishList)
                } // end of if
            } // end of try
            catch (e: JSONException) {
                Log.d("populatDatabase", "$e")
            }
        } // end of prepopulateData()

        fun loadJSONArray(context: Context): JSONArray {
            val inputStream = context.resources.openRawResource(R.raw.fishingregulations)

            BufferedReader(inputStream.reader()).use {
                return JSONArray(it.readText())
            }
        }

    } // end of callback

    companion object {
        // For singleton instantiation
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            // Create and prepopulate the database.
            // if the INSTANCE is not null, then return it
            // if it is, then create the table
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fish_database"
                ).addCallback(AppdatabaseCallback(context, scope)).build()
                INSTANCE = instance
                instance
            } // end of synchronized
        }
    }


}


