package com.omni.feature_favorite_genere.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.omni.feature_favorite_genere.databinding.FragmentGeneresBinding
import com.omni.feature_favorite_genere.viewmodel.GeneresViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class GenresFragment : Fragment() {
    private lateinit var binding: FragmentGeneresBinding
    private val adapter = GeneresListAdapter { obj ->
        Toast.makeText(context, obj.name, Toast.LENGTH_SHORT).show()
    }
    private val viewModel: GeneresViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentGeneresBinding.inflate(layoutInflater, container, false).let {
            binding = it
        }.also {
            binding.generesRecyclerView.adapter = adapter
            observeGeneresViewModel()
            return binding.root
        }
    }

    private fun observeGeneresViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.result.collect {
                adapter.submitList(it)
            }
        }
    }
}