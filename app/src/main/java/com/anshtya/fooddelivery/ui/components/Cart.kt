package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.local.CartEntity

@Composable
fun CartItem(
    cartItem: CartEntity,
    onQuantityDecrease: () -> Unit,
    onQuantityIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.fillMaxWidth(0.5F)
            ) {
                Text(
                    text = cartItem.foodName
                )

                Text(
                    text = stringResource(R.string.price, cartItem.foodPrice)
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                QuantitySelector(
                    quantity = cartItem.quantity,
                    onQuantityIncrease = onQuantityIncrease,
                    onQuantityDecrease = onQuantityDecrease,
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .height(40.dp)
                )

                Text(
                    text = stringResource(R.string.price, cartItem.totalPrice)
                )
            }
        }
    }
}