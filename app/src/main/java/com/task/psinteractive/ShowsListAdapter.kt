package com.task.psinteractive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.psinteractive.data.ShowInfo
import com.task.psinteractive.databinding.ShowInfoItemBinding

class ShowsListAdapter:
    ListAdapter<ShowInfo, ShowsListAdapter.ShowViewHolder>(DiffCallback) {

    /**
     * The ShowViewHolder constructor takes the binding variable from the associated
     * ShowInfoItem, which gives it access to the [ShowInfo] information.
     */
    class ShowViewHolder(private var binding: ShowInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(showInfo: ShowInfo) {
            binding.show = showInfo
            // Forces the data binding to execute immediately, which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ShowInfo] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ShowInfo>() {
        override fun areItemsTheSame(oldItem: ShowInfo, newItem: ShowInfo): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: ShowInfo, newItem: ShowInfo): Boolean {
            val oldItemDuration = oldItem.startTime?.let {
                oldItem.endTime?.minus(it)
            }
            val newItemDuration = newItem.startTime?.let {
                newItem.endTime?.minus(it)
            }
            return oldItemDuration == newItemDuration &&
                    oldItem.title == newItem.title &&
                    oldItem.episodeTitle == newItem.episodeTitle
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            ShowInfoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = getItem(position)
        holder.bind(show)
    }
}