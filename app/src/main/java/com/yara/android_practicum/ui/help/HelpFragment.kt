package com.yara.android_practicum.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentHelpBinding
import kotlinx.coroutines.launch

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by navGraphViewModels<HelpViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.help_fragment_label)

        // init adapter
        val adapter = CategoriesRecyclerAdapter()

        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager = GridLayoutManager(activity, RECYCLER_GRID_COLUMNS)

        val space = (resources.displayMetrics.density * RECYCLER_GRID_SPACING).toInt() //converting dp to pixels
        binding.rvCategories.addItemDecoration(SpacingItemDecorator(space)) //setting space between items in RecyclerView

        // load data
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .collect {
                        hideProgressBar()
                        adapter.addItems(it.categories)
                    }
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

    private fun hideProgressBar() {
        binding.pbProgressBar.visibility = View.GONE
    }

    companion object {
        const val RECYCLER_GRID_COLUMNS = 2
        const val RECYCLER_GRID_SPACING = 8
    }
}