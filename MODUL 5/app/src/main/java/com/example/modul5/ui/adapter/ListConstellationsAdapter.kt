package com.example.modul5.ui.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.modul5.data.local.ConstellationEntity
import com.example.modul5.databinding.StarItemBinding

class ListConstellationsAdapter(
    private val onWebClick: (ConstellationEntity) -> Unit,
    private val onDetailClick: (ConstellationEntity) -> Unit
) : ListAdapter<ConstellationEntity, ListConstellationsAdapter.ViewHolder>(DIFF_CALLBACK) {

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
            val radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16f, holder.itemView.context.resources.displayMetrics
            ).toInt()

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .transform(RoundedCorners(radius))
                .into(holder.binding.img)


            btnWeb.setOnClickListener { onWebClick(item) }
            btnDetail.setOnClickListener { onDetailClick(item) }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConstellationEntity>() {
            override fun areItemsTheSame(oldItem: ConstellationEntity, newItem: ConstellationEntity) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ConstellationEntity, newItem: ConstellationEntity) =
                oldItem == newItem
        }
    }
}