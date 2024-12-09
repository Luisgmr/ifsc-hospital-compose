package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun HospitalSelect(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    onFieldFocused: (() -> Unit)? = null, // Evento disparado quando o campo é clicado
    modifier: Modifier = Modifier,
    placeholder: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is androidx.compose.foundation.interaction.FocusInteraction.Focus) {
                onFieldFocused?.invoke() // Dispara o evento quando o campo recebe foco
            }
        }
    }

    Box(modifier.clickable(
        onClick = { expanded = !expanded },
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    )) {
        Column {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                label = { Text(label) },
                placeholder = { if (placeholder != null) Text(placeholder) },
                shape = MaterialTheme.shapes.medium,
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                },
                interactionSource = interactionSource, // Interações monitoradas aqui
                modifier = modifier
            )

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
