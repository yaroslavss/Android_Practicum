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

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        event?.let {
            binding.toolbar.title = it.title
            binding.tvEventTitle.text = it.title
            binding.tvEventDateString.text = it.dateString
            val imageMain = context?.resources?.getIdentifier(
                event.images.first(),
                "drawable",
                context?.packageName
            )
            imageMain.let {
                binding.ivEventImageMain.setImageResource(it as Int)
            }
            val image2 = context?.resources?.getIdentifier(
                event.images.get(1),
                "drawable",
                context?.packageName
            )
            image2.let {
                binding.ivEventImage2.setImageResource(it as Int)
            }
            val image3 = context?.resources?.getIdentifier(
                event.images.get(2),
                "drawable",
                context?.packageName
            )
            image3.let {
                binding.ivEventImage3.setImageResource(it as Int)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}