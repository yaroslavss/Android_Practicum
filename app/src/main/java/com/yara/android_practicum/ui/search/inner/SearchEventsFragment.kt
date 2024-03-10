package com.yara.android_practicum.ui.search.inner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentSearchEventsBinding
import com.yara.android_practicum.ui.search.SearchViewModel

class SearchEventsFragment : Fragment() {

    private var _binding: FragmentSearchEventsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

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
        divider.setDrawable(resources.getDrawable(R.drawable.search_recycler_divider, null))
        binding.rvSearchResults.addItemDecoration(divider)

        viewModel.searchResultsLiveData.observe(viewLifecycleOwner) { list ->
            adapter.results = list
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}