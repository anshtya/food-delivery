package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anshtya.fooddelivery.data.Food
import com.anshtya.fooddelivery.ui.theme.ratingGreenColor

@Composable
fun FoodItem(
    item: Food,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 5.dp,
        modifier = modifier
            .clickable { onFoodClick(item.id) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            FoodImage(
                imageUrl = item.image,
                contentDescription = item.name,
                modifier = Modifier.height(200.dp)
            )
            FoodDescription(
                name = item.name,
                price = item.price,
                rating = item.rating,
                type = item.type,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun FoodImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier
) {
    Surface(
        color = Color.LightGray,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun FoodDescription(
    name: String,
    price: Double,
    rating: Double,
    type: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            FoodRatingSurface(
                rating = rating
            )
        }
        Text(
            text = "$$price",
            style = MaterialTheme.typography.titleSmall
        )
        if (type.isNotEmpty()) {
            Text(
                text = type,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FoodRatingSurface(
    rating: Double
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = ratingGreenColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = rating.toString(),
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}