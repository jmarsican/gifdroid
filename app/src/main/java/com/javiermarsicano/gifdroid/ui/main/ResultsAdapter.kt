package com.javiermarsicano.gifdroid.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.gifdroid.R
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.databinding.EntryItemBinding
import com.javiermarsicano.gifdroid.extensions.setImageUrl

class ResultsAdapter(val onItemClickListener: (Content) -> Unit) :
    RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {

    private val items: MutableList<Content> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val binding = EntryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            entryTitle.text = item.title
            entryImage.setImageUrl(
                item.images.original.url,
                crossFade = true,
                error = R.mipmap.ic_launcher,
                placeholder = R.mipmap.ic_launcher,
            )
        }
    }

    fun addItems(newItems: List<Content>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    class ResultsViewHolder(val binding: EntryItemBinding): RecyclerView.ViewHolder(binding.root)
}