package com.satdev.rickandmortyapp.presentation.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.databinding.FragmentTransformBinding
import com.satdev.rickandmortyapp.presentation.adapters.CharacterAdapter
import com.satdev.rickandmortyapp.presentation.comparators.CharacterComparator
import com.satdev.rickandmortyapp.presentation.ui.character.detail.CharacterDetailFragment
import com.satdev.rickandmortyapp.presentation.ui.listeners.CharacterAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
@AndroidEntryPoint
class CharacterFragment : Fragment(),CharacterAdapterListener {

    private val characterViewModel: CharacterViewModel by viewModels()
    private var _binding: FragmentTransformBinding? = null
    val pagingAdapter = CharacterAdapter(CharacterComparator,this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransformBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewTransform

        recyclerView.adapter = pagingAdapter
        /*
        transformViewModel.texts.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

         */
        viewLifecycleOwner.lifecycleScope.launch {
            characterViewModel.flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        /*
        transformViewModel.getCharacters().observe(viewLifecycleOwner, Observer {

        })

         */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getItemSelected(position: Int?, character: Character?) {
        println("$position $character")
        //val action = CharacterDetailFragmentDirections
        val action  = CharacterFragmentDirections.actionNavCharactersToCharacterDetailFragment(character)
        findNavController().navigate(action)

    }
}