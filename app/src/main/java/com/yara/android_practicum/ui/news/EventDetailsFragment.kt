package com.yara.android_practicum.ui.news

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yara.android_practicum.databinding.FragmentEventDetailsBinding
import com.yara.android_practicum.domain.model.Event
import com.yara.android_practicum.utils.Constants

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val event = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(Constants.PARCELABLE_EVENT_KEY, Event::class.java)
        } else {
            arguments?.getParcelable(Constants.PARCELABLE_EVENT_KEY) as Event?
        }

        val navController = findNavController()

        binding.toolbar.title = event?.title
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}