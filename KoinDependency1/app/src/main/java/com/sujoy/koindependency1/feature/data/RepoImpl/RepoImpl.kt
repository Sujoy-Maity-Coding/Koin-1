package com.sujoy.koindependency1.feature.data.RepoImpl

import com.sujoy.koindependency1.feature.data.api.SomeApi
import com.sujoy.koindependency1.feature.domain.Repository

class RepoImpl(private val someApi: SomeApi):Repository {
    override suspend fun getData(): String {
        someApi
        return "data 1"
    }
}