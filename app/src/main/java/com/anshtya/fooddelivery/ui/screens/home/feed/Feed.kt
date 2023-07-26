package com.anshtya.fooddelivery.ui.screens.home.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.FoodDeliveryBottomNavBar
import com.anshtya.fooddelivery.ui.components.FoodDeliverySnackbar
import com.anshtya.fooddelivery.ui.components.FoodItem
import com.anshtya.fooddelivery.ui.components.LocationBar
import com.anshtya.fooddelivery.ui.screens.home.HomeSections
import com.anshtya.fooddelivery.ui.util.SnackbarData
import kotlinx.coroutines.launch

@Composable
fun Feed(
    onFoodClick: (Int) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    onNavigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    feedViewModel: FeedViewModel = hiltViewModel()
) {
    val recommendedFoodItems = remember { feedViewModel.recommendedFoodList }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val cartNotEmpty by feedViewModel.cartNotEmpty.collectAsStateWithLifecycle()
    val totalItems by feedViewModel.totalItems.collectAsStateWithLifecycle()
    val totalPrice by feedViewModel.totalPrice.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = {
            FoodDeliverySnackbar(
                snackbarHostState = snackbarHostState,
                snackbarData = SnackbarData(totalItems, totalPrice),
                onClick = onNavigateToCart
            )
        },
        bottomBar = {
            FoodDeliveryBottomNavBar(
                currentRoute = HomeSections.FEED.route,
                onNavigateToRoute = onNavigateToRoute
            )
        }
    ) { innerPadding ->
        LaunchedEffect(cartNotEmpty) {
            if (cartNotEmpty) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "",
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier.padding(innerPadding)
        ) {
            item {
                LocationBar()
            }
            item {
                Text(
                    text = stringResource(R.string.recommended),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(
                items = recommendedFoodItems,
            ) { item ->
                FoodItem(
                    item = item,
                    onFoodClick = onFoodClick
                )
            }
        }
    }
}