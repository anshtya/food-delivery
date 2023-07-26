package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 5.dp,
            modifier = modifier
                .height(80.dp)
                .padding(10.dp)
                .noRippleClickable { onClick() }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    stringResource(R.string.total_items_added, snackbarData.totalItems)
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.Red,
                    modifier = Modifier
                        .width(100.dp)
                        .noRippleClickable { onClick() }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.price, snackbarData.totalPrice),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.view_cart),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}