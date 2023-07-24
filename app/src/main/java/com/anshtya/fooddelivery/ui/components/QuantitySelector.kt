package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R

@Composable
fun QuantitySelector(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.Blue),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_remove_24),
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.noRippleClickable { }
            )
            Text("1")
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.noRippleClickable { }
            )
        }
    }
}