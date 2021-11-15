package com.omni.featur_search.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.omni.featur_search.R
import com.omni.featur_search.databinding.FragmentSearchBinding
import com.omni.featur_search.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()

    private val searchListAdapter = SearchListAdapter {
        Timber.d(it.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentSearchBinding.inflate(layoutInflater, container, false).let {
            _binding = it
        }.also {
            _binding.lifecycleOwner = this
            _binding.serachViewModel = viewModel
            _binding.fragmentSearchMarketsRv.adapter = searchListAdapter
            configureSearchView()
            obServeViewModel()
            return _binding.root
        }
    }


    private fun configureSearchView() {
        with(_binding.searchView) {
            val v: View? =
                findViewById(R.id.search_plate)
            v?.setBackgroundColor(resources.getColor(android.R.color.transparent))

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    Timber.e(query)
                        viewModel.fetchGamesByName(query ?: "")
                    return true
                }
            })
        }

    }


    private fun obServeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.searchResult.collectLatest {
                searchListAdapter.submitList(it)
            }
        }
    }

}