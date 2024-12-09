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
import com.luisgmr.ifsc.hospital.controller.LaboratorioController
import com.luisgmr.ifsc.hospital.model.Laboratorio
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButtonForLaboratorios {
    NOME_FANTASIA, CONTATO
}

@Composable
fun LaboratorioScreen(
    controller: LaboratorioController = LaboratorioController(),
    navController: NavController,
    onBack: () -> Unit
) {
    val laboratorios = remember { mutableStateListOf<Laboratorio>() }
    val filteredLaboratorios = remember { mutableStateListOf<Laboratorio>() }
    val selectedButtonForLaboratorios = remember { mutableStateOf(SelectedButtonForLaboratorios.NOME_FANTASIA) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    // Load laboratorios when the screen starts
    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadLaboratorios()
        laboratorios.clear()
        laboratorios.addAll(controller.getLaboratorios())
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
    LaunchedEffect(debounceQuery, selectedButtonForLaboratorios.value) {
        filteredLaboratorios.clear()
        filteredLaboratorios.addAll(
            laboratorios.filter { laboratorio ->
                when (selectedButtonForLaboratorios.value) {
                    SelectedButtonForLaboratorios.NOME_FANTASIA -> laboratorio.nomeFantasia.contains(debounceQuery, ignoreCase = true)
                    SelectedButtonForLaboratorios.CONTATO -> laboratorio.contato.contains(debounceQuery, ignoreCase = true)
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
                    Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Voltar", Modifier.size(24.dp))
                }
                Text(
                    text = "Menu de Laboratórios",
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
                    SelectableButton("Nome Fantasia", { selectedButtonForLaboratorios.value = SelectedButtonForLaboratorios.NOME_FANTASIA }, selectedButtonForLaboratorios.value == SelectedButtonForLaboratorios.NOME_FANTASIA)
                    SelectableButton("Contato", { selectedButtonForLaboratorios.value = SelectedButtonForLaboratorios.CONTATO }, selectedButtonForLaboratorios.value == SelectedButtonForLaboratorios.CONTATO)
                }

                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = when (selectedButtonForLaboratorios.value) {
                        SelectedButtonForLaboratorios.NOME_FANTASIA -> "Buscar por nome fantasia"
                        SelectedButtonForLaboratorios.CONTATO -> "Buscar por contato"
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
                        "Buscando laboratórios..."
                    } else {
                        "${filteredLaboratorios.size} laboratórios encontrados"
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
                // Tabela com cabeçalhos
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = MaterialTheme.shapes.large
                            ),
                    )
                    PaginatedDataTable(
                        headerBackgroundColor = Color.Transparent,
                        modifier = Modifier.fillMaxWidth()
                            .background(color = Color.Transparent, shape = MaterialTheme.shapes.large),
                        rowBackgroundColor = { Color.White },
                        footerBackgroundColor = Color.White,
                        columns = listOf(
                            DataColumn {
                                Text(
                                    text = "Nome Fantasia",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                            DataColumn {
                                Text(
                                    text = "Contato",
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
                        filteredLaboratorios.forEach { laboratorio ->
                            row {
                                cell {
                                    Text(
                                        text = laboratorio.nomeFantasia ?: "",
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                cell { Text(laboratorio.contato ?: "") }
                                cell { Text(laboratorio.status ?: "") }
                            }
                        }
                    }

                    // Linha separadora
                    Box(
                        modifier = Modifier
                            .offset(y = 51.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.White)
                    )

                    // Botão de cadastrar
                    Button(
                        onClick = {
                            navController.navigate(Screen.CADASTRO_LABORATORIO)
                        },
                        modifier = Modifier.align(Alignment.BottomStart),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text("Cadastrar Laboratório")
                    }
                }
            }
        })
    }
}
