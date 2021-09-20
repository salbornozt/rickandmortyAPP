package com.satdev.rickandmortyapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.databinding.ItemTransformBinding

class CharacterAdapter(diffCallback: DiffUtil.ItemCallback<Character>): PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemTransformBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemTransformBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(character: Character){
            binding.apply {
                binding.textViewItemTransform.setText(character.name)
                try {
                    Glide.with(binding.imageViewItemTransform).load(character.image).into(binding.imageViewItemTransform)
                } catch (e: Exception) {
                }
            }
        }

    }
}