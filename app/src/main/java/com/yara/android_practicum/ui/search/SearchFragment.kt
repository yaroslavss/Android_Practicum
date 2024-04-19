package com.yara.android_practicum.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.yara.android_practicum.databinding.FragmentSearchBinding
import com.yara.android_practicum.ui.news.NewsViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.Locale
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<NewsViewModel>()

    private val allDisposables = CompositeDisposable()

    private lateinit var adapter: VPAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabNames: Array<String> = arrayOf(
        "По мероприятиям",
        "По НКО",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup ViewPager2 adapter
        adapter = VPAdapter(this)
        viewPager = binding.vpViewPager
        viewPager.adapter = adapter

        // init tabs
        tabLayout = binding.tlTabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        // init search view
        val result = binding.svSearch.queryTextChanges()
            // delay input
            .debounce(SEARCH_STRING_DELAY, TimeUnit.MILLISECONDS)
            .map {
                it.toString().lowercase(Locale.getDefault()).trim()
            }
            .subscribe(
                { str ->
                    viewModel.filterEventsByTitle(str)
                },
                { exception ->
                    println("!!! ${exception.message}")
                }
            )

        allDisposables.add(result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        allDisposables.clear()
    }

    companion object  {
        const val SEARCH_STRING_DELAY = 500L
    }
}