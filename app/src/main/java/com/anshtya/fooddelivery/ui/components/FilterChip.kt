package com.anshtya.fooddelivery.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.anshtya.fooddelivery.data.Filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipWithBottomSheet(
    filterName: String,
    selected: Boolean,
    optionList: List<Filter>,
    selectMultipleOptions: Boolean,
    onOptionApply: (Set<Int>) -> Unit
) {
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var optionSelected by remember { mutableStateOf(setOf<Int>()) }

    FilterChip(
        selected = selected,
        onClick = { isBottomSheetOpen = true },
        label = {
            Text(filterName)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    )

    if (isBottomSheetOpen) {
        FoodDeliveryBottomSheet(
            optionSelected = optionSelected,
            optionsList = optionList,
            selectMultipleOptions = selectMultipleOptions,
            onSheetClose = { isBottomSheetOpen = false },
            onRemoveOption = { id ->
                optionSelected = optionSelected.minus(id)
            },
            onAddOption = { id ->
                optionSelected = optionSelected.plus(id)
            },
            onAddSingleOptionOnly = { id ->
                optionSelected = setOf(id)
            },
            onClearAllOptions = {
                optionSelected = setOf()
                onOptionApply(optionSelected)
                isBottomSheetOpen = false
            },
            onOptionApply = {
                onOptionApply(optionSelected)
                isBottomSheetOpen = false
            }
        )
    }
}