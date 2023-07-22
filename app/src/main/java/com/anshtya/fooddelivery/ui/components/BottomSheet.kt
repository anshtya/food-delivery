package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.data.FilterOption
import com.anshtya.fooddelivery.data.SortOption

@Composable
fun SortOptionBottomSheet(
    sortOptions: List<SortOption>,
    selectedSortOption: SortOption,
    onSortOptionClick: (SortOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        sortOptions.forEach { sortOption ->
            if (sortOption != SortOption.Default) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSortOptionClick(sortOption) }
                ) {
                    Text(text = sortOption.name)
                    Checkbox(
                        checked = selectedSortOption == sortOption,
                        onCheckedChange = { onSortOptionClick(sortOption) }
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.4F)
                    .height(50.dp)
            ) {
                Text("Clear All")
            }
            Button(
                enabled = false,
                onClick = {  },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Apply")
            }
        }
    }
}

@Composable
fun FilterOptionsBottomSheet(
    filterOptions: List<FilterOption>,
    selectedFilterOptions: List<FilterOption>,
    onFilterOptionClick: (FilterOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        filterOptions.forEach { filter ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onFilterOptionClick(filter) }
            ) {
                Text(text = filter.name)
                Checkbox(
                    checked = selectedFilterOptions.contains(filter),
                    onCheckedChange = { onFilterOptionClick(filter) }
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.4F)
                    .height(50.dp)
            ) {
                Text("Clear All")
            }
            Button(
                enabled = false,
                onClick = { },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Apply")
            }
        }
    }
}