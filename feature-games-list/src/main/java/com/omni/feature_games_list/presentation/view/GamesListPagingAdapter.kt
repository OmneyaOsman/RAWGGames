package com.omni.feature_games_list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.omni.domain.entities.GameEntity
import com.omni.feature_games_list.databinding.ListItemGameBinding

class GamesListPagingAdapter(val onClick: (model: GameEntity) -> Unit)
    : PagingDataAdapter<GameEntity, GamesViewHolder>(GAMES_COMPARATOR) {

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder =
        LayoutInflater.from(parent.context).let {
            ListItemGameBinding.inflate(it, parent, false)
        }.let { GamesViewHolder(it) }

    companion object {
        val GAMES_COMPARATOR = object : DiffUtil.ItemCallback<GameEntity>() {
            override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean =
                oldItem.name == newItem.name

        }
    }
}