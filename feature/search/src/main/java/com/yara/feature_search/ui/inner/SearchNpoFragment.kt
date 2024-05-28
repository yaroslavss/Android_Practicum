package com.yara.feature_search.ui.inner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.core.ui.news.NewsViewModel
import com.yara.feature_search.R
import com.yara.feature_search.databinding.FragmentSearchNpoBinding
import kotlinx.coroutines.launch

class SearchNpoFragment : Fragment() {

    private var _binding: FragmentSearchNpoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchNpoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init default view
        switchInitialLayout(true)

        // init adapter
        val adapter = SearchResultsRecyclerAdapter(listOf())

        binding.rvSearchResults.adapter = adapter
        binding.rvSearchResults.layoutManager = LinearLayoutManager(activity)

        val divider = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        val drawable = ResourcesCompat.getDrawable(
            context?.getResources()!!,
            R.drawable.search_recycler_divider,
            null
        )
        if (drawable != null) {
            divider.setDrawable(drawable)
        }

        binding.rvSearchResults.addItemDecoration(divider)

        // load data from uiState
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.searchResults.isEmpty()) {
                        switchInitialLayout(true)
                    } else {
                        switchInitialLayout(false)
                    }
                    adapter.results = state.searchResults
                    adapter.notifyDataSetChanged()

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchInitialLayout(on: Boolean) {
        binding.apply {
            // turn off
            tvSearchKeysLabel.isVisible = !on
            tvSearchResultsLabel.isVisible = !on
            mdDivider1.isVisible = !on
            mdDivider2.isVisible = !on
            // turn on
            ivZoomIcon.isVisible = on
            tvSearchDescLabel.isVisible = on
            tvSearchExampleLabel.isVisible = on
        }
    }
}