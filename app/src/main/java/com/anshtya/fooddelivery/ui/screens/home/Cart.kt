package com.anshtya.fooddelivery.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.BottomButton
import com.anshtya.fooddelivery.ui.screens.HomeViewModel

@Composable
fun Cart(
    onNavigateToRoute: (String) -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val cartItems by homeViewModel.cartItems.collectAsStateWithLifecycle()
    Scaffold(
        bottomBar = { CartBottomBar() }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items = cartItems,
                key = { cartItems ->
                    cartItems.food.id
                }
            ) {

            }
        }
    }
}

@Composable
fun CartBottomBar() {
    Column(Modifier.fillMaxWidth()) {
        Divider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Divider(thickness = Dp.Hairline)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomButton(
                    onClick = {}
                ) {
                    Text(
                        stringResource(R.string.checkout)
                    )
                }
            }
        }
    }
}