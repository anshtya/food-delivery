package com.anshtya.fooddelivery.ui.components

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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.local.model.Food
import com.anshtya.fooddelivery.data.local.model.foodList
import com.anshtya.fooddelivery.ui.theme.ratingGreenColor
import com.anshtya.fooddelivery.ui.util.noRippleClickable

@Composable
fun FoodItem(
    item: Food,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 5.dp,
        modifier = modifier
            .height(180.dp)
            .noRippleClickable { onFoodClick(item.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            FoodDescription(
                item = item,
                modifier = Modifier.fillMaxWidth(0.5F)
            )

            FoodImage(
                imageUrl = item.image,
                contentDescription = item.name,
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

@Composable
fun FoodDescription(
    item: Food,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        FoodRatingSurface(
            rating = item.rating
        )
        Text(
            text = stringResource(R.string.price, item.price),
            style = MaterialTheme.typography.titleSmall
        )
        if (item.type.isNotEmpty()) {
            Text(
                text = item.type,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FoodImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(5.dp),
) {
    Surface(
        color = Color.LightGray,
        shape = shape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
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

@Preview(showBackground = true)
@Composable
fun FoodPreview() {
    FoodItem(
        item = foodList[8],
        onFoodClick = {}
    )
}