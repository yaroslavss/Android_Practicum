package com.yara.android_practicum.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.repository.PhotoDialogMenuItemsRepositoryImpl
import com.yara.android_practicum.domain.model.PhotoDialogMenuItem
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