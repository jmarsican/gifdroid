package com.javiermarsicano.gifdroid.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.gifdroid.R
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.databinding.EntryItemBinding
import com.javiermarsicano.gifdroid.extensions.setImageUrl

class ResultsAdapter(val onItemClickListener: (Content) -> Unit) :
    RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {

    private val items: MutableList<Content> = mutableListOf()
    val favoriteIds: MutableSet<String> = mutableSetOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = EntryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            entryTitle.text = item.title
            entryId.text = item.id
            entryImage.setImageUrl(
                item.images.original.url,
                crossFade = true,
                error = R.mipmap.ic_launcher,
                placeholder = R.mipmap.ic_launcher,
            )
            val isFavourite = favoriteIds.contains(item.id)
            entryGoBtn.visibility  = if (isFavourite) View.VISIBLE else View.INVISIBLE
        }
    }

    fun addItems(newItems: List<Content>) {
        val diffResult = DiffUtil.calculateDiff(DiffResult(this.items, this.items + newItems))
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    class DiffResult(private val mValues: List<Content>, private val newValues: List<Content>): DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mValues[oldItemPosition].id == newValues[newItemPosition].id
        }

        override fun getOldListSize() = mValues.size

        override fun getNewListSize() = newValues.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val newItem = newValues[newItemPosition]
            val oldItem = mValues[oldItemPosition]
            return (newItem.id == oldItem.id
                    && newItem.title == oldItem.title
                    && newItem.slug == oldItem.slug)
        }
    }

    class ResultsViewHolder(val binding: EntryItemBinding): RecyclerView.ViewHolder(binding.root)
}