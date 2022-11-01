package com.sheridan.stn991602827.fishingregulation.data




import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.sheridan.stn991602827.fishingregulation.databinding.RecyclerviewItemBinding


class FishingListAdaptor : ListAdapter<FishingRegulation, FishingListAdaptor.FishingViewHolder>(
    FishingComparator()
) {
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
    }


class FishingComparator : DiffUtil.ItemCallback<FishingRegulation>() {
    override fun areItemsTheSame(oldItem: FishingRegulation, newItem: FishingRegulation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FishingRegulation,
        newItem: FishingRegulation
    ): Boolean {
        return oldItem.specie == newItem.specie
    }

}

class FishingViewHolder(private var binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fishingRegulation: FishingRegulation) {
        binding.fishingRegulation = fishingRegulation
    }

}
}
