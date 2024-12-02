package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HospitalNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    content: @Composable RowScope.() -> Unit = {},
    label: @Composable () -> Unit
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        label = label,
        unselectedContentColor = Color.LightGray,
        selectedContentColor = Color.White,

    )
}