package com.sujoy.koindependency1.feature.domain

interface Repository {
    suspend fun getData():String
}