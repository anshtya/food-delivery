package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.data.local.CartEntity
import java.text.DecimalFormat

@Composable
fun CartItem(
    cartItem: CartEntity,
    onQuantityDecrease: () -> Unit,
    onQuantityIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    val df = remember { DecimalFormat("#.##") }
    Surface(
        shape = RoundedCornerShape(5.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
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
                    text = cartItem.foodPrice.toString()
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
                    modifier = Modifier.fillMaxWidth(0.4F)
                )

                Text(
                    text = df.format(cartItem.totalPrice)
                )
            }
        }
    }
}