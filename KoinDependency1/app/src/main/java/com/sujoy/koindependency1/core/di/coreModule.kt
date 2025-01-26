package com.sujoy.koindependency1.core.di

import android.content.Context
import com.sujoy.koindependency1.core.MainViewModel
import com.sujoy.koindependency1.core.data.RepoImpl2
import com.sujoy.koindependency1.feature.domain.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val coreModule = module{
    single { androidContext().getSharedPreferences("pref", Context.MODE_PRIVATE) }
    single<Repository> (qualifier= named("RepositoryImpl2")){ RepoImpl2(get()) }
    viewModel { MainViewModel(get(named("RepositoryImpl2"))) }
}