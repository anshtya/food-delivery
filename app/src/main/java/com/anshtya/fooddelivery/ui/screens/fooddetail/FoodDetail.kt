package com.anshtya.fooddelivery.ui.screens.fooddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.FoodDescription
import com.anshtya.fooddelivery.ui.components.FoodImage
import com.anshtya.fooddelivery.ui.components.QuantitySelector

@Composable
fun FoodDetail(
    foodId: String,
    foodDetailViewModel: FoodDetailViewModel = viewModel()
) {
    val foodItem = remember { foodDetailViewModel.getFoodDetail(foodId.toInt()) }

    Scaffold(
        bottomBar = { BottomCartBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FoodImage(
                imageUrl = foodItem.image,
                shape = RectangleShape,
                contentDescription = null,
                modifier = Modifier.height(200.dp)
            )
            FoodDescription(
                item = foodItem,
                modifier = Modifier.padding(10.dp)
            )
            Divider()
            DetailContent(Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.detail_text)
        )
    }
}

@Composable
fun BottomCartBar(
    modifier: Modifier = Modifier
) {
    Divider()
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        QuantitySelector(
            modifier = Modifier
                .fillMaxWidth(0.3F)
                .height(50.dp)
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                stringResource(R.string.add_to_cart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    FoodDetail(
        foodId = "1"
    )
}