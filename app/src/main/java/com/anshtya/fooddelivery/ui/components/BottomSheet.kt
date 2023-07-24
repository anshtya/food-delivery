package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.R
import com.anshtya.fooddelivery.data.FilterOption
import com.anshtya.fooddelivery.data.SortOption

@Composable
fun SortOptionBottomSheet(
    sortOptions: List<SortOption>,
    selectedSortOption: SortOption,
    onApplySortOption: (SortOption) -> Unit,
    onClearSortOption: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LazyColumn {
            items(sortOptions) { sortOption ->
                if(sortOption != SortOption.Default) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onApplySortOption(sortOption) }
                    ) {
                        Text(text = sortOption.optionName)
                        Checkbox(
                            checked = selectedSortOption == sortOption,
                            onCheckedChange = { onApplySortOption(sortOption) }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomButton(
                onClick = onClearSortOption,
                enabled = selectedSortOption != SortOption.Default
            ) {
                Text(
                    stringResource(R.string.clear_all)
                )
            }
        }
    }
}

@Composable
fun FilterOptionsBottomSheet(
    filterOptions: List<FilterOption>,
    selectedFilterOptions: List<FilterOption>,
    onFilterOptionClick: (FilterOption) -> Unit,
    onClearFilterOption: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn {
            items(filterOptions) { filter ->
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
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                enabled = selectedFilterOptions.isNotEmpty(),
                onClick = onClearFilterOption,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    stringResource(R.string.clear_all)
                )
            }
        }
    }
}