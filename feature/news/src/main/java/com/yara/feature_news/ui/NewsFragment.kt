package com.yara.feature_news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yara.core.utils.Constants
import com.yara.feature_news.R
import com.yara.feature_news.databinding.FragmentNewsBinding
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getString(R.string.news_fragment_label)
        val navController = findNavController()

        // init adapter
        val adapter = EventsRecyclerAdapter { event ->
            val bundle = Bundle();
            bundle.putParcelable(Constants.PARCELABLE_EVENT_KEY, event)
            navController.navigate(R.id.eventDetailsFragment, bundle)
        }

        binding.rvEvents.adapter = adapter
        binding.rvEvents.layoutManager = LinearLayoutManager(activity)

        // load data from uiState
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    hideProgressBar()
                    adapter.differ.submitList(state.events)
                }
            }
        }

        // proceed toolbar menu item click
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_filter) {
                navController.navigate(R.id.filterGraph)
            }
            true
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
}