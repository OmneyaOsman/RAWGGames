package com.omni.feature_games_list.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.omni.domain.entities.GameEntity
import com.omni.feature_games_list.databinding.ListItemGameBinding

class GamesViewHolder(private val binding: ListItemGameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(gameEntity: GameEntity) {
        binding.gameEntity = gameEntity
        binding.executePendingBindings()
    }
}