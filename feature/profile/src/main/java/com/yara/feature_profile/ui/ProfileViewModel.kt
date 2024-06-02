package com.yara.feature_profile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.feature_profile.data.datasource.HardCodedDataSource
import com.yara.feature_profile.data.mapper.toDomainModelList
import com.yara.feature_profile.data.repository.PhotoDialogMenuItemsRepositoryImpl
import com.yara.feature_profile.domain.model.PhotoDialogMenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _menuItemsListLiveData = MutableLiveData<List<PhotoDialogMenuItem>>()
    val menuItemsListLiveData: LiveData<List<PhotoDialogMenuItem>> = _menuItemsListLiveData

    private val menuItemsRepository = PhotoDialogMenuItemsRepositoryImpl(HardCodedDataSource())

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val menuItems = menuItemsRepository.loadMenuItems()
            _menuItemsListLiveData.postValue(menuItems.toDomainModelList())
        }
    }
}