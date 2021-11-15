package com.omni.feature_favorite_genere.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omni.domain.entities.generes.GenereEntity
import com.omni.feature_favorite_genere.databinding.ListItemGenereBinding

class GeneresListAdapter(val onClick: (model: GenereEntity) -> Unit) :
    ListAdapter<GenereEntity, GeneresListAdapter.GeneresViewHolder>(Generes_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneresViewHolder =
        LayoutInflater.from(parent.context).let {
            ListItemGenereBinding.inflate(it, parent, false)
        }.let { GeneresViewHolder(it) }

    override fun onBindViewHolder(holder: GeneresViewHolder, position: Int) {
        getItem(position)?.let {genere->
            holder.bind(genere)
            holder.binding.genereCard.setOnClickListener {
                onClick(genere)
            }
        }
    }

    class GeneresViewHolder( val binding: ListItemGenereBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genereEntity: GenereEntity) {
            binding.genereEntity = genereEntity
            binding.executePendingBindings()
        }
    }

    companion object {
        val Generes_COMPARATOR = object : DiffUtil.ItemCallback<GenereEntity>() {
            override fun areContentsTheSame(oldItem: GenereEntity, newItem: GenereEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: GenereEntity, newItem: GenereEntity): Boolean =
                oldItem.name == newItem.name

        }
    }


}