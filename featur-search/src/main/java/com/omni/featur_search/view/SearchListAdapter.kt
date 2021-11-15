package com.omni.featur_search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omni.domain.entities.GameEntity
import com.omni.featur_search.databinding.ListItemSearchBinding

class SearchListAdapter(val onClick: (model: GameEntity) -> Unit) :
    ListAdapter<GameEntity, SearchListAdapter.GeneresViewHolder>(Generes_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneresViewHolder =
        LayoutInflater.from(parent.context).let {
            ListItemSearchBinding.inflate(it, parent, false)
        }.let { GeneresViewHolder(it) }

    override fun onBindViewHolder(holder: GeneresViewHolder, position: Int) {
        getItem(position)?.let {genere->
            holder.bind(genere)
            holder.binding.gameCard.setOnClickListener {
                onClick(genere)
            }
        }
    }

    class GeneresViewHolder( val binding: ListItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(GameEntity: GameEntity) {
            binding.game = GameEntity
            binding.executePendingBindings()
        }
    }

    companion object {
        val Generes_COMPARATOR = object : DiffUtil.ItemCallback<GameEntity>() {
            override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean =
                oldItem.name == newItem.name

        }
    }


}