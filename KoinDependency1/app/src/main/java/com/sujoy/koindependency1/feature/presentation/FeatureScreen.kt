package com.sujoy.koindependency1.feature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureScreen(modifier: Modifier = Modifier, viewModel: FeatureViewModel= koinViewModel()) {
    LaunchedEffect(true) {
        println("we got 1 : ${viewModel.getData()}")
    }
}