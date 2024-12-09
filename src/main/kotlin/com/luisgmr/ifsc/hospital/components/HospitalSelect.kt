package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun HospitalSelect(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    onFieldFocused: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String = "Valor obrigatório",
    isEnabled: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is androidx.compose.foundation.interaction.FocusInteraction.Focus) {
                onFieldFocused?.invoke()
            }
        }
    }

    Box(modifier) {
        Box(
            modifier =
                if (isEnabled) {
                    Modifier.clickable(
                        onClick = { expanded = !expanded },
                        interactionSource = interactionSource,
                        indication = null)
                        .zIndex(2.0f)
                        .matchParentSize()
                } else {
                    Modifier
                }
        ) {

        }
        Column {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                label = { Text(label) },
                placeholder = { if (placeholder != null) Text(placeholder) },
                shape = MaterialTheme.shapes.medium,
                enabled = isEnabled,
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                },
                interactionSource = interactionSource,
                modifier = modifier
            )
            if (isError) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onValueChange(option)
                        expanded = false
                    }) {
                        Text(option)
                    }
                }
            }
        }
    }
}
