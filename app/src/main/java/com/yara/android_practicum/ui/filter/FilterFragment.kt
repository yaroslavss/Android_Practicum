package com.yara.android_practicum.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentFilterBinding
import com.yara.android_practicum.ui.news.NewsViewModel
import com.yara.android_practicum.utils.Resource

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.filter_fragment_label)

        // init adapter
        val adapter = CategoriesRecyclerAdapter()

        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.search_recycler_divider, null))
        binding.rvCategories.addItemDecoration(divider)

        // load data from LiveData
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> adapter.addItems(resource.data)
                is Resource.Error -> showError(view, resource.message.toString())
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}