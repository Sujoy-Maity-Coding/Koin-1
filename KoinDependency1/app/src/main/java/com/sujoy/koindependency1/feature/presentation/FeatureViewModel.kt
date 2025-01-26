package com.sujoy.koindependency1.feature.presentation

import androidx.lifecycle.ViewModel
import com.sujoy.koindependency1.feature.data.RepoImpl.RepoImpl
import com.sujoy.koindependency1.feature.domain.Repository

class FeatureViewModel(private val repo:Repository):ViewModel() {
    suspend fun getData():String{
        return repo.getData()
    }
}