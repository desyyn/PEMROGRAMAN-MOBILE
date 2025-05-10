package com.example.starconstellations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starconstellations.databinding.StarItemBinding


class ListConstellationsAdapter(
    private val list: List<Constellation>,
    private val onWebClick: (String) -> Unit,
    private val onDetailClick: (Constellation) -> Unit
) : RecyclerView.Adapter<ListConstellationsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: StarItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            tvName.text = item.name
            tvDescription.text = item.description
            tvYear.text = item.year
            Glide.with(img.context).load(item.imageResId).into(img)

            btnWeb.setOnClickListener {
                onWebClick(item.webUrl)
            }

            btnDetail.setOnClickListener {
                onDetailClick(item)
            }
        }
    }
}