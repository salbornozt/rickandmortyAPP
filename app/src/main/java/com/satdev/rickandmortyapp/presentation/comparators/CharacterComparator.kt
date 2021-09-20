package com.satdev.rickandmortyapp.presentation.comparators

import androidx.recyclerview.widget.DiffUtil
import com.satdev.rickandmortyapp.data.model.Character

object  CharacterComparator : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}