package com.sheridan.stn991602827.fishingregulation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sheridan.stn991602827.fishingregulation.data.FishingListAdaptor
import com.sheridan.stn991602827.fishingregulation.data.FishingRegulationViewModel
import androidx.lifecycle.observe
import com.sheridan.stn991602827.fishingregulation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fishingRegulationViewModel
        get() = FishingRegulationViewModel((application as InitialApplication).repository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var zoneId = 2
        var list = fishingRegulationViewModel.allZoneRegulations(zoneId)

        val adaptor = FishingListAdaptor()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adaptor

        list.observe(owner = this){ regulations ->
            regulations.let {
                adaptor.submitList(it)
            }
        }


/*
        // add
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FishingListAdaptor()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fishingRegulationViewModel.allZoneRegulations(1).observe(owner = this) { regulations ->
            regulations.let { adapter.submitList(it) }
        }

 */
    }//end of onCreate
} // end of mainActivity




