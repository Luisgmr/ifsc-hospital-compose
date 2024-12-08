package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.Screen
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalTextField
import com.luisgmr.ifsc.hospital.components.SelectableButton
import com.luisgmr.ifsc.hospital.controller.AcompanhanteController
import com.luisgmr.ifsc.hospital.model.Acompanhante
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButtonForAcompanhantes {
    NOME, CPF
}

@Composable
fun AcompanhanteScreen(
    controller: AcompanhanteController = AcompanhanteController(),
    navController: NavController,
    onBack: () -> Unit
) {
    val acompanhantes = remember { mutableStateListOf<Acompanhante>() }
    val filteredAcompanhantes = remember { mutableStateListOf<Acompanhante>() }
    val selectedButtonForAcompanhantes = remember { mutableStateOf(SelectedButtonForAcompanhantes.NOME) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    // Load acompanhantes when the screen starts
    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadAcompanhantes()
        acompanhantes.clear()
        acompanhantes.addAll(controller.getAcompanhantes())
        isLoading = false
    }

    // Debounce logic for search input
    LaunchedEffect(searchQuery) {
        isLoading = true
        kotlinx.coroutines.delay(500) // Simulating debounce
        debounceQuery = searchQuery
        isLoading = false
    }

    // Filter logic
    LaunchedEffect(debounceQuery, selectedButtonForAcompanhantes.value) {
        filteredAcompanhantes.clear()
        filteredAcompanhantes.addAll(
            acompanhantes.filter { acompanhante ->
                when (selectedButtonForAcompanhantes.value) {
                    SelectedButtonForAcompanhantes.NOME -> acompanhante.nome.contains(debounceQuery, ignoreCase = true)
                    SelectedButtonForAcompanhantes.CPF -> acompanhante.cpf.contains(debounceQuery, ignoreCase = true)
                }
            }
        )
    }

    HospitalTheme {
        HospitalContent(content = {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Back", Modifier.size(24.dp))
                }
                Text(
                    text = "Menu de Acompanhantes",
                    style = MaterialTheme.typography.h3
                )
            }

            // Filter Options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableButton("Nome", { selectedButtonForAcompanhantes.value = SelectedButtonForAcompanhantes.NOME }, selectedButtonForAcompanhantes.value == SelectedButtonForAcompanhantes.NOME)
                    SelectableButton("CPF", { selectedButtonForAcompanhantes.value = SelectedButtonForAcompanhantes.CPF }, selectedButtonForAcompanhantes.value == SelectedButtonForAcompanhantes.CPF)
                }

                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = when (selectedButtonForAcompanhantes.value) {
                        SelectedButtonForAcompanhantes.NOME -> "Buscar por nome do acompanhante"
                        SelectedButtonForAcompanhantes.CPF -> "Buscar por CPF do acompanhante"
                    },
                    modifier = Modifier.padding(16.dp),
                    icon = FontAwesomeIcons.Solid.Search,
                )
            }

            // Status Message
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isLoading) {
                        "Buscando acompanhantes..."
                    } else {
                        "${filteredAcompanhantes.size} acompanhantes encontrados"
                    },
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(Modifier.size(8.dp))

            // Main Content
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                // Data Table
                PaginatedDataTable(
                    headerBackgroundColor = Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
                        .background(color = Color.Transparent, shape = MaterialTheme.shapes.large),
                    rowBackgroundColor = { Color.White },
                    footerBackgroundColor = Color.White,
                    columns = listOf(
                        DataColumn {
                            Text(
                                text = "Nome",
                                modifier = Modifier.offset(x = 16.dp),
                                color = Color.White
                            )
                        },
                        DataColumn {
                            Text(
                                text = "CPF",
                                modifier = Modifier.offset(x = 16.dp),
                                color = Color.White
                            )
                        },
                        DataColumn {
                            Text(
                                text = "Parentesco",
                                modifier = Modifier.offset(x = 16.dp),
                                color = Color.White
                            )
                        },
                        DataColumn {
                            Text(
                                text = "Status",
                                modifier = Modifier.offset(x = 16.dp),
                                color = Color.White
                            )
                        },
                    ),
                    state = rememberPaginatedDataTableState(7),
                ) {
                    filteredAcompanhantes.forEach { acompanhante ->
                        row {
                            cell {
                                Text(
                                    text = acompanhante.nome ?: "",
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            cell { Text(acompanhante.cpf ?: "") }
                            cell { Text(acompanhante.grauParentesco ?: "") }
                            cell { Text(acompanhante.status ?: "") }
                        }
                    }
                }

                // Register Button
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.CADASTRO_ACOMPANHANTE)
                        },
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(text = "Cadastrar Acompanhante")
                    }
                }
            }
        })
    }
}
