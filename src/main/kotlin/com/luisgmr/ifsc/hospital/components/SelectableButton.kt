package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectableButton(
    label: String,
    onClick: () -> Unit,
    selected: Boolean
) {
    // Cria uma fonte de interação mutável
    val interactionSource = MutableInteractionSource()

    Row(
        modifier = Modifier
            .background(
                color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                shape = MaterialTheme.shapes.medium,
            )
            .border(
                border = if (selected) BorderStroke(0.dp, Color.Transparent) else BorderStroke(1.dp, MaterialTheme.colors.background),
                shape = MaterialTheme.shapes.medium
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = true,
                    color = Color.White.copy(alpha = 0.1f)
                ),
            )
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        Text(
            text = label,
            color = if (selected) Color.White else MaterialTheme.colors.secondary,
            modifier = Modifier.padding(PaddingValues(vertical = 4.dp, horizontal = 8.dp))
        )
    }
}