package com.yara.android_practicum.ui.search.inner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentSearchEventsBinding
import com.yara.android_practicum.ui.news.NewsViewModel
import com.yara.android_practicum.utils.Resource

class SearchEventsFragment : Fragment() {

    private var _binding: FragmentSearchEventsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        viewModel.searchResultsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    switchInitialLayout()
                    adapter.results = resource.data
                    adapter.notifyDataSetChanged()
                }

                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun switchInitialLayout() {
        binding.apply {
            // turn on
            tvSearchKeysLabel.isVisible = true
            tvSearchResultsLabel.isVisible = true
            mdDivider1.isVisible = true
            mdDivider2.isVisible = true
            // turn off
            ivZoomIcon.isVisible = false
            tvSearchDescLabel.isVisible = false
            tvSearchExampleLabel.isVisible = false
        }
    }
}