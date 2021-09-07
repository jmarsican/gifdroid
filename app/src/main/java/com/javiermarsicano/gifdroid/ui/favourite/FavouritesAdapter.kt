package com.javiermarsicano.gifdroid.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.gifdroid.R
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.databinding.FavouriteItemBinding
import com.javiermarsicano.gifdroid.extensions.setImageUrl

class FavouritesAdapter(val onItemClickListener: (Favourite) -> Unit) :
    RecyclerView.Adapter<FavouritesAdapter.FavouriteViewHolder>() {

    private val items: MutableList<Favourite> = mutableListOf()
    val favoriteIds: MutableSet<String> = mutableSetOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            favouriteImage.setImageUrl(
                item.url,
                crossFade = true,
                error = R.mipmap.ic_launcher,
                placeholder = R.mipmap.ic_launcher,
            )
        }
    }

    fun addItems(newItems: List<Favourite>) {
        val diffResult = DiffUtil.calculateDiff(DiffResult(this.items, this.items + newItems))
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    class DiffResult(private val mValues: List<Favourite>, private val newValues: List<Favourite>): DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mValues[oldItemPosition].id == newValues[newItemPosition].id
        }

        override fun getOldListSize() = mValues.size

        override fun getNewListSize() = newValues.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val newItem = newValues[newItemPosition]
            val oldItem = mValues[oldItemPosition]
            return (newItem.id == oldItem.id
                    && newItem.url == oldItem.url)
        }
    }

    class FavouriteViewHolder(val binding: FavouriteItemBinding): RecyclerView.ViewHolder(binding.root)
}