package com.yara.android_practicum.ui.news

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.FragmentEventDetailsBinding
import com.yara.core.domain.model.Event
import com.yara.android_practicum.utils.Constants

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

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
            binding.apply {
                toolbar.title = it.title
                tvEventTitle.text = it.title
                tvEventDateString.text = it.dateString
                tvSponsorTitle.text = it.organisation
                tvSponsorAddress.text = it.address
                tvSponsorPhone.text = it.phone
                tvEventText.text = it.description
            }

            if (event.images.size > 0) {
                showImage(event.images.first(), binding.ivEventImageMain)
            }

            if (event.images.size > 1) {
                showImage(event.images.get(1), binding.ivEventImage2)
            }

            if (event.images.size > 2) {
                showImage(event.images.get(2), binding.ivEventImage3)
            }

            // set badge for bottom navigation view
            viewModel.setEventRead(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showImage(imageToShow: String, imageView: ImageView) {
        // icon from network
        if (imageToShow.startsWith("http", true)) {
            Glide.with(requireActivity())
                .load(imageToShow)
                .placeholder(R.drawable.news_img_1)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(imageView)
        } else {
            // icon from json asset file
            imageView.setImageResource(
                context?.resources?.getIdentifier(
                    imageToShow,
                    "drawable",
                    context?.packageName
                ) as Int
            )
        }
    }
}