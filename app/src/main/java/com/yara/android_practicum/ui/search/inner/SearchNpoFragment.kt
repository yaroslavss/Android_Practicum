package com.yara.android_practicum.ui.search.inner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentSearchNpoBinding
import com.yara.android_practicum.ui.news.NewsViewModel
import com.yara.android_practicum.utils.Resource

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

        // init adapter
        val adapter = SearchResultsRecyclerAdapter(listOf())

        binding.rvSearchResults.adapter = adapter
        binding.rvSearchResults.layoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.search_recycler_divider, null))
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
            tvSearchKeysLabel.visibility = View.VISIBLE
            tvSearchResultsLabel.visibility = View.VISIBLE
            mdDivider1.visibility = View.VISIBLE
            mdDivider2.visibility = View.VISIBLE
            // turn off
            ivZoomIcon.visibility = View.INVISIBLE
            tvSearchDescLabel.visibility = View.INVISIBLE
            tvSearchExampleLabel.visibility = View.INVISIBLE
        }
    }
}