package com.anshtya.fooddelivery.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.BottomButton
import com.anshtya.fooddelivery.ui.components.CartItem
import com.anshtya.fooddelivery.ui.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(
    onNavigateBack: () -> Unit,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by cartViewModel.cartItems.collectAsStateWithLifecycle()

    LaunchedEffect(cartItems) {
        cartViewModel.cartNotEmpty.collect {
            if (!it) {
                onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.cart)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .size(25.dp)
                            .noRippleClickable { onNavigateBack() }
                    )
                }
            )
        },
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
                    cartItems.id
                }
            ) { cartItem ->
                CartItem(
                    cartItem = cartItem,
                    onQuantityDecrease = { cartViewModel.decreaseQuantity(cartItem.foodId) },
                    onQuantityIncrease = { cartViewModel.increaseQuantity(cartItem.foodId) }
                )
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