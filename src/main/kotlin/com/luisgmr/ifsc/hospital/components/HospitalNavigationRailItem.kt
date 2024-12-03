package com.luisgmr.ifsc.hospital.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HospitalNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit
) {
    val scale = remember { Animatable(1f) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    scale.animateTo(0.95f)
                }
                is PressInteraction.Release, is PressInteraction.Cancel -> {
                    scale.animateTo(1f)
                }
                is HoverInteraction.Enter -> {
                    scale.animateTo(1.05f)
                }
                is HoverInteraction.Exit -> {
                    scale.animateTo(1f)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .scale(scale.value)
            .padding(4.dp)
            .hoverable(interactionSource)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            }
            .background(
                color = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.2f) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        CompositionLocalProvider(LocalContentColor provides if (selected) Color.White else MaterialTheme.colors.onSecondary) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                icon()
                label()
            }
        }
    }
}

