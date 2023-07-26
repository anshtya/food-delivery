package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.util.SnackbarData

@Composable
fun FoodDeliverySnackbar(
    snackbarHostState: SnackbarHostState,
    snackbarData: SnackbarData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SnackbarHost(snackbarHostState) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = Color.LightGray,
            modifier = Modifier
                .padding(10.dp)
                .noRippleClickable { onClick() }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    stringResource(R.string.total_items_added, snackbarData.totalItems)
                )
                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = onClick
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            stringResource(R.string.price, snackbarData.totalPrice)
                        )
                        Text(
                            stringResource(R.string.view_cart)
                        )
                    }
                }
            }
        }
    }
}