package com.omni.feature_games_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.omni.core.extensions.initializeToView
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.omni.feature_games_list.databinding.FragmentGamesListBinding
import com.omni.feature_games_list.presentation.viewmodel.GamesListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.UnknownHostException

class GamesListFragment : Fragment() {

    private lateinit var binding: FragmentGamesListBinding
    private val adapter = GamesListPagingAdapter { obj ->
        Toast.makeText(context, obj.id, Toast.LENGTH_SHORT).show()
    }
    private val viewModel: GamesListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentGamesListBinding.inflate(layoutInflater, container, false).let {
            binding = it
        }.also {
            return binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initGamesListRecyclerView()
        initializeSwipeToRefresh()
        lifecycleScope.launchWhenStarted {
            viewModel.genereState.collect { genere ->
                viewModel.getGames(genere).collect {
                    if (binding.srLayout.isRefreshing)
                        binding.srLayout.isRefreshing = false
                    adapter.submitData(it)
                }
            }
        }


        observeViewModel()
    }

    private fun initGamesListRecyclerView() {
        binding.gamesRecyclerView.adapter = adapter

        lifecycleScope.launch {
            adapter.loadStateFlow.distinctUntilChanged()
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    if (adapter.itemCount == 0) viewModel.isEmptyListEvent.emit(true)
                }
        }

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.gamesRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            viewModel.dataLoading.value = loadState.source.refresh is LoadState.Loading

            if (loadState.source.refresh is LoadState.Error)
                lifecycleScope.launchWhenStarted {
                    Toast.makeText(context, "network error", Toast.LENGTH_SHORT)
                        .show()

                }
            else {
                val errorState: LoadState.Error? = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    lifecycleScope.launchWhenStarted {
                        if (it.error is UnknownHostException)
                            Toast.makeText(
                                context,
                                "Please check your Network ",
                                Toast.LENGTH_SHORT
                            ).show()
                        else
                            Toast.makeText(context, "${it.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.isEmptyListEvent.collect {
//                if (it) {
//                    binding.rvQuestions.hide()
//                    binding.emptyView.show()
//                } else {
//                    binding.rvQuestions.show()
//                    binding.emptyView.hide()
//                }

            }
        }

        viewModel.dataLoading.observe(viewLifecycleOwner, {
            binding.progressBar.isVisible = it
        })

    }

    private fun initializeSwipeToRefresh() {
        binding.srLayout.initializeToView {
            lifecycleScope.launchWhenStarted {
                viewModel.isEmptyListEvent.emit(false)
            }
            adapter.refresh()
        }
    }
}



