package com.sujoy.koindependency1.feature.di

import com.sujoy.koindependency1.feature.data.RepoImpl.RepoImpl
import com.sujoy.koindependency1.feature.data.api.SomeApi
import com.sujoy.koindependency1.feature.domain.Repository
import com.sujoy.koindependency1.feature.presentation.FeatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val featureModule= module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SomeApi.BASE_URL)
            .build()
            .create(SomeApi::class.java)
    }
    single<Repository>(qualifier= named("RepositoryImpl")) { RepoImpl(get()) }
//    singleOf(::RepoImpl).bind<Repository>()

    viewModel { FeatureViewModel(get(named("RepositoryImpl"))) }
}