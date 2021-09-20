package com.satdev.rickandmortyapp.presentation.ui.transform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.satdev.rickandmortyapp.R
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.databinding.FragmentTransformBinding
import com.satdev.rickandmortyapp.databinding.ItemTransformBinding
import com.satdev.rickandmortyapp.presentation.adapters.CharacterAdapter
import com.satdev.rickandmortyapp.presentation.comparators.CharacterComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
@AndroidEntryPoint
class TransformFragment : Fragment() {

    private val transformViewModel: TransformViewModel by viewModels()
    private var _binding: FragmentTransformBinding? = null
    val pagingAdapter = CharacterAdapter(CharacterComparator)

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
            transformViewModel.flow.collectLatest {
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

    class TransformAdapter() :
        ListAdapter<Character, TransformViewHolder>(object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }) {



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformViewHolder {
            val binding = ItemTransformBinding.inflate(LayoutInflater.from(parent.context))
            return TransformViewHolder(binding)
        }

        override fun onBindViewHolder(holder: TransformViewHolder, position: Int) {
            holder.textView.text = getItem(position).name
            try {
                Glide.with(holder.imageView).load(getItem(position).image).into(holder.imageView)
            } catch (e: Exception) {
            }
            /*
            holder.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(holder.imageView.resources, drawables[Random.nextInt(0,15)], null)
            )

             */
        }
    }

    class TransformViewHolder(binding: ItemTransformBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageView: ImageView = binding.imageViewItemTransform
        val textView: TextView = binding.textViewItemTransform
    }
}