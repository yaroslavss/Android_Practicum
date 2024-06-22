package com.yara.feature_news.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yara.core.domain.model.Event
import com.yara.core.utils.CallbackListener
import com.yara.core.utils.Constants
import com.yara.feature_news.R
import com.yara.feature_news.databinding.FragmentEventDetailsBinding
import com.yara.feature_news.di.NewsComponentProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private val viewModel by activityViewModels<NewsViewModel> {
        viewModelFactory
    }

    var event: Event? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as NewsComponentProvider)
            .getNewsComponent()
            .injectEventDetailsFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)

        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NavigationBarEventDetails(::openHelpDialog)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getInt(Constants.INTENT_EVENT_KEY)

        if (eventId != null) {
            viewModel.queryEventById(eventId)

            // load data from uiState
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiStateEvent
                        .collect { state ->
                            event = state.event
                            drawUi()
                        }
                }
            }
        }

        val navController = findNavController()

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun drawUi() {
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

            if (event!!.images.size > 0) {
                showImage(event!!.images.first(), binding.ivEventImageMain)
            }

            if (event!!.images.size > 1) {
                showImage(event!!.images.get(1), binding.ivEventImage2)
            }

            if (event!!.images.size > 2) {
                showImage(event!!.images.get(2), binding.ivEventImage3)
            }

            // set badge for bottom navigation view
            viewModel.setEventRead(it)
        }
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

    private fun openHelpDialog() {
        val dialogFragment = HelpDialogFragment(
            requireActivity() as CallbackListener,
            event?.id,
            event?.title,
        )
        dialogFragment.show(requireActivity().supportFragmentManager, "HELP_DIALOG")
    }
}