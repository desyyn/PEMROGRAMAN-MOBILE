package com.example.modul4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modul4.databinding.StarItemBinding

class ListConstellationsAdapter(
    private val onWebClick: (Constellation) -> Unit,
    private val onDetailClick: (Constellation) -> Unit
) : ListAdapter<Constellation, ListConstellationsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: StarItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvName.text = item.name
            tvDescription.text = item.description
            tvYear.text = item.year
            Glide.with(img.context).load(item.imageResId).into(img)

            btnWeb.setOnClickListener { onWebClick(item) }
            btnDetail.setOnClickListener { onDetailClick(item) }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Constellation>() {
            override fun areItemsTheSame(oldItem: Constellation, newItem: Constellation): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Constellation, newItem: Constellation): Boolean {
                return oldItem == newItem
            }
        }
    }
}
