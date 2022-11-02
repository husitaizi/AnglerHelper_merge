package com.sheridan.stn991602827.fishingregulation.data


import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat

import com.sheridan.stn991602827.fishingregulation.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sheridan.stn991602827.fishingregulation.MainActivity
import com.sheridan.stn991602827.fishingregulation.databinding.RecyclerviewItemBinding


class FishingListAdaptor : ListAdapter<FishingRegulation, FishingListAdaptor.FishingViewHolder>(
    FishingComparator()
) {

    //  create a collection of all drawables resources
    private var srcList = mutableMapOf<String, Int>(
        "white_sucker" to R.drawable.white_sucker,
        "rainbow_trout" to R.drawable.rainbow_trout,
        "northern_pike" to R.drawable.northern_pike,
        "muskellunge" to R.drawable.muskellunge,
        "goldeye" to R.drawable.goldeye,
        "brown_bullhead" to R.drawable.brown_bullhead,
        "brook_trout" to R.drawable.brook_trout,
    )

    // set image as drawable
/*
    val context=holder.itemView.context

    var srcList= mutableMapOf<String,Drawable?>(
        "white_sucker" to getDrawable(context,R.drawable.white_sucker),
        "rainbow_trout" to getDrawable(context,R.drawable.rainbow_trout),
        "northern_pike" to getDrawable(context,R.drawable.northern_pike),
        "muskellunge" to getDrawable(context,R.drawable.muskellunge),
        "goldeye" to getDrawable(context,R.drawable.goldeye),
        "brown_bullhead" to getDrawable(context,R.drawable.brown_bullhead),
        "brook_trout" to getDrawable(context,R.drawable.brook_trout),
    )

    imageView.setImageDrawable(srcList[imgName])

 */


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishingViewHolder {
        return FishingViewHolder(

            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FishingViewHolder, position: Int) {
        val currentRegulation = getItem(position)
        holder.bind(
            currentRegulation
        )
        // TODO: set corresponding image src according to "specie"
        // get the specie
        var imgName = currentRegulation.specie.replace(" ", "_")
        var imageView = holder.itemView.findViewById<ImageView>(R.id.fish_image)

        // match the specie name with list
        imageView.setImageResource(srcList[imgName]!!)

    } // end of onBindViewHolder


    class FishingComparator : DiffUtil.ItemCallback<FishingRegulation>() {
        override fun areItemsTheSame(
            oldItem: FishingRegulation,
            newItem: FishingRegulation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FishingRegulation,
            newItem: FishingRegulation
        ): Boolean {
            return oldItem.specie == newItem.specie
        }

    }

    class FishingViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fishingRegulation: FishingRegulation) {
            binding.fishingRegulation = fishingRegulation
        }

    }
}
