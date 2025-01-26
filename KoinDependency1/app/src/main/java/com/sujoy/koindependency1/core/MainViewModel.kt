package com.sujoy.koindependency1.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujoy.koindependency1.feature.domain.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {
    init {
        viewModelScope.launch {
            println("we got 2 : ${repository.getData()}")
        }
    }
}