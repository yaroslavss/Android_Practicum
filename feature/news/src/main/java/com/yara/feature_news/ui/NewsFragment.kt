package com.yara.feature_news.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.yara.core.domain.model.Event
import com.yara.core.utils.Constants
import com.yara.feature_news.R
import com.yara.feature_news.di.NewsComponentProvider
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private val viewModel by activityViewModels<NewsViewModel> {
        viewModelFactory
    }

    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as NewsComponentProvider)
            .getNewsComponent()
            .injectNewsFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NewsScreen(viewModel, ::navigateToFilterFragment, ::navigateToEventDetails)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    private fun navigateToFilterFragment() {
        navController.navigate(R.id.filterGraph)
    }

    private fun navigateToEventDetails(event: Event) {
        val bundle = Bundle();
        bundle.putParcelable(Constants.PARCELABLE_EVENT_KEY, event)
        navController.navigate(R.id.eventDetailsFragment, bundle)
    }
}