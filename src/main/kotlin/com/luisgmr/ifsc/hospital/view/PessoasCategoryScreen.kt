package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.SelectableButton
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButton {
    PACIENTE1, PACIENTE2, PACIENTE3
}

@Composable
fun PessoasCategoryScreen(category: String, onBack: () -> Unit) {
    val selectedButton = remember { mutableStateOf(SelectedButton.PACIENTE1) }
    HospitalTheme {
        HospitalContent(content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableButton("Pacientes", { selectedButton.value = SelectedButton.PACIENTE1 }, selectedButton.value == SelectedButton.PACIENTE1)
                    SelectableButton("Pacientes", { selectedButton.value = SelectedButton.PACIENTE2 }, selectedButton.value == SelectedButton.PACIENTE2)
                    SelectableButton("Pacientes", { selectedButton.value = SelectedButton.PACIENTE3 }, selectedButton.value == SelectedButton.PACIENTE3)
                }
                TextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text("Pesquisar")
                    },
                    trailingIcon = {
                        Icon(FontAwesomeIcons.Solid.Search, contentDescription = null, Modifier.size(16.dp))
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
            }
        })
    }
}