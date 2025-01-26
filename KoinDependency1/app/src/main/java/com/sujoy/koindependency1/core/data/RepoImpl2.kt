package com.sujoy.koindependency1.core.data

import android.content.SharedPreferences
import com.sujoy.koindependency1.feature.data.api.SomeApi
import com.sujoy.koindependency1.feature.domain.Repository

class RepoImpl2(private val preferences: SharedPreferences):Repository {
    override suspend fun getData(): String {
        preferences
        return "data2"
    }
}