package com.yara.android_practicum.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SearchViewModel : ViewModel() {

    private val _searchResultsLiveData = MutableLiveData<List<String>>()
    val searchResultsLiveData: LiveData<List<String>> = _searchResultsLiveData

    private val charPool: List<Char> = ('а'..'я') + ('А'..'Я') + ('0'..'9')

    init {
        getSearchResults()
    }

    fun getSearchResults() {
        _searchResultsLiveData.postValue(
            listOf<String>(
                getString(),
                getString(),
                getString(),
                getString(),
                getString(),
            )
        )
    }

    private fun getString() = (1..STRING_LENGTH)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")

    companion object {
        const val STRING_LENGTH = 40
    }
}