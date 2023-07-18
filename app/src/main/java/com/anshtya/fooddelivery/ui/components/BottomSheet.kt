package com.anshtya.fooddelivery.ui.components

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anshtya.fooddelivery.data.Filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDeliveryBottomSheet(
    optionsList: List<Filter>,
    selectMultipleOptions: Boolean,
    onSheetClose: () -> Unit,
    onOptionApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    var optionSelected by remember { mutableStateOf(setOf<Int>()) }

    Log.d("lulu", "$optionSelected")

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
                                optionSelected = if (optionSelected.contains(filter.id)) {
                                    optionSelected.minus(filter.id)
                                } else {
                                    optionSelected.plus(filter.id)
                                }
                            }
                    ) {
                        Text(text = filter.name)
                        Checkbox(
                            checked = optionSelected.contains(filter.id),
                            onCheckedChange = {
                                optionSelected = if (optionSelected.contains(filter.id)) {
                                    optionSelected.minus(filter.id)
                                } else {
                                    optionSelected.plus(filter.id)
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
                                optionSelected = if (optionSelected.contains(filter.id)) {
                                    setOf()
                                } else {
                                    setOf(filter.id)
                                }
                            }
                    ) {
                        Text(text = filter.name)
                        RadioButton(
                            selected = optionSelected.contains(filter.id),
                            onClick = {
                                optionSelected = if (optionSelected.contains(filter.id)) {
                                    setOf()
                                } else {
                                    setOf(filter.id)
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
                        onClick = { optionSelected = setOf() },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.4F)
                            .height(50.dp)
                    ) {
                        Text("Clear All")
                    }
                    Button(
                        enabled = optionSelected.isNotEmpty(),
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
    }
}