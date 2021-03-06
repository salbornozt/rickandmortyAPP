package com.satdev.rickandmortyapp.presentation.ui.character.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.satdev.rickandmortyapp.R
import com.satdev.rickandmortyapp.databinding.CharacterDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {



    private val viewModel: CharacterDetailViewModel by viewModels()

    private lateinit var binding: CharacterDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(view).load(viewModel.image).into(characterImageView)
            characterName.setText(viewModel.name)
            characterOrigin.setText(viewModel.location.name)
        }
    }


}