package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R

@Composable
fun FoodDeliverySearchBar(
    text: String,
    isSearching: Boolean,
    onValueChange: (String) -> Unit,
    onClearText: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 5.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = text,
                onValueChange = { onValueChange(it) },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty() && !isSearching) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            modifier = Modifier.noRippleClickable { onClearText() }
                        )
                    }
                },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.search_food)) },
                modifier = Modifier.weight(1F)
            )
            if (isSearching) {
                CircularProgressIndicator(
                    color = Color.Gray,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(15.dp)
                )
            }
        }
    }
}