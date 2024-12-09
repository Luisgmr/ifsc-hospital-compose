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
import com.luisgmr.ifsc.hospital.controller.ExameController
import com.luisgmr.ifsc.hospital.model.Exame
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButtonForExames {
    TITULO, TIPO
}

@Composable
fun ExameScreen(
    controller: ExameController = ExameController(),
    navController: NavController,
    onBack: () -> Unit
) {
    val exames = remember { mutableStateListOf<Exame>() }
    val filteredExames = remember { mutableStateListOf<Exame>() }
    val selectedButtonForExames = remember { mutableStateOf(SelectedButtonForExames.TITULO) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    // Load exames when the screen starts
    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadExames()
        exames.clear()
        exames.addAll(controller.getExames())
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
    LaunchedEffect(debounceQuery, selectedButtonForExames.value) {
        filteredExames.clear()
        filteredExames.addAll(
            exames.filter { exame ->
                when (selectedButtonForExames.value) {
                    SelectedButtonForExames.TITULO -> exame.tituloExame.contains(debounceQuery, ignoreCase = true)
                    SelectedButtonForExames.TIPO -> exame.tipoExame.contains(debounceQuery, ignoreCase = true)
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
                    text = "Menu de Exames",
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
                    SelectableButton("Título", { selectedButtonForExames.value = SelectedButtonForExames.TITULO }, selectedButtonForExames.value == SelectedButtonForExames.TITULO)
                    SelectableButton("Tipo", { selectedButtonForExames.value = SelectedButtonForExames.TIPO }, selectedButtonForExames.value == SelectedButtonForExames.TIPO)
                }

                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = when (selectedButtonForExames.value) {
                        SelectedButtonForExames.TITULO -> "Buscar por título do exame"
                        SelectedButtonForExames.TIPO -> "Buscar por tipo do exame"
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
                        "Buscando exames..."
                    } else {
                        "${filteredExames.size} exames encontrados"
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
                                    text = "Título",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                            DataColumn {
                                Text(
                                    text = "Tipo",
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
                        filteredExames.forEach { exame ->
                            row {
                                cell {
                                    Text(
                                        text = exame.tituloExame,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                cell { Text(exame.tipoExame) }
                                cell { Text(exame.status) }
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
                            navController.navigate(Screen.CADASTRO_EXAME)
                        },
                        modifier = Modifier.align(Alignment.BottomStart),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text("Cadastrar Exame")
                    }
                }
            }
        })
    }
}
