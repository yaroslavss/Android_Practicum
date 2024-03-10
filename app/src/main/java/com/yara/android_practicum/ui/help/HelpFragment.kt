package com.yara.android_practicum.ui.help

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HelpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.help_fragment_label)

        // init adapter
        val adapter = CategoriesRecyclerAdapter()

        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager = GridLayoutManager(activity, RECYCLER_GRID_COLUMNS)

        val x = (resources.displayMetrics.density * RECYCLER_GRID_SPACING).toInt() //converting dp to pixels
        binding.rvCategories.addItemDecoration(SpacingItemDecorator(x)) //setting space between items in RecyclerView

        viewModel.categoriesListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.addItems(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val RECYCLER_GRID_COLUMNS = 2
        const val RECYCLER_GRID_SPACING = 8
    }
}