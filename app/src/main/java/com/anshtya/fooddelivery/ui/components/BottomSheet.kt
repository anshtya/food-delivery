package com.anshtya.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.data.Filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDeliveryBottomSheet(
    optionSelected: Set<Int>,
    optionsList: List<Filter>,
    selectMultipleOptions: Boolean,
    onSheetClose: () -> Unit,
    onRemoveOption: (Int) -> Unit,
    onAddOption: (Int) -> Unit,
    onAddSingleOptionOnly: (Int) -> Unit,
    onClearAllOptions: () -> Unit,
    onOptionApply: () -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        onDismissRequest = onSheetClose,
        modifier = modifier.height(300.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            if (selectMultipleOptions) {
                items(
                    items = optionsList
                ) { filter ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (optionSelected.contains(filter.id)) {
                                    onRemoveOption(filter.id)
                                } else {
                                    onAddOption(filter.id)
                                }
                            }
                    ) {
                        Text(text = filter.name)
                        Checkbox(
                            checked = optionSelected.contains(filter.id),
                            onCheckedChange = {
                                if (optionSelected.contains(filter.id)) {
                                    onRemoveOption(filter.id)
                                } else {
                                    onAddOption(filter.id)
                                }
                            }
                        )
                    }
                }
            } else {
                items(
                    items = optionsList
                ) { filter ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .noRippleClickable {
                                if (optionSelected.contains(filter.id)) {
                                    onClearAllOptions()
                                } else {
                                    onAddSingleOptionOnly(filter.id)
                                }
                            }
                    ) {
                        Text(text = filter.name)
                        RadioButton(
                            selected = optionSelected.contains(filter.id),
                            onClick = {
                                if (optionSelected.contains(filter.id)) {
                                    onClearAllOptions()
                                } else {
                                    onAddSingleOptionOnly(filter.id)
                                }
                            }
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onClearAllOptions,
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.4F)
                            .height(50.dp)
                    ) {
                        Text("Clear All")
                    }
                    Button(
                        enabled = optionSelected.isNotEmpty(),
                        onClick = onOptionApply,
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
    }
}