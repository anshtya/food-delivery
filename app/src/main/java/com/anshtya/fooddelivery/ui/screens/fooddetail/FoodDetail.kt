package com.anshtya.fooddelivery.ui.screens.fooddetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.ui.components.FoodDescription
import com.anshtya.fooddelivery.ui.components.FoodImage
import com.anshtya.fooddelivery.ui.screens.HomeViewModel

@Composable
fun FoodDetail(
    foodId: String,
    homeViewModel: HomeViewModel = viewModel()
) {
    val foodItem = remember { homeViewModel.getFoodDetail(foodId.toInt()) }
    
    Column(
        modifier = Modifier.fillMaxSize()
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
        Divider(Modifier.height(2.dp))
        DetailContent(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
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

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    FoodDetail(
        foodId = "1"
    )
}