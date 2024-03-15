package com.yara.android_practicum.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentNewsBinding
import com.yara.android_practicum.utils.Resource

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
        val adapter = EventsRecyclerAdapter()

        binding.rvEvents.adapter = adapter
        binding.rvEvents.layoutManager = LinearLayoutManager(activity)

        // load data from LiveData
        viewModel.eventsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> adapter.differ.submitList(resource.data)
                is Resource.Error -> showError(view, resource.message.toString())
                else -> {}
            }
        }

        // proceed toolbar menu item click
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_filter) {
                navController.navigate(R.id.filterFragment)
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
}