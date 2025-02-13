package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.Screen
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalTextField
import com.luisgmr.ifsc.hospital.controller.QuartoController
import com.luisgmr.ifsc.hospital.model.Quarto
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState

@Composable
fun QuartoScreen(
    controller: QuartoController = QuartoController(),
    onBack: () -> Unit,
    navigateToRegister: () -> Unit
) {
    val quartos = remember { mutableStateListOf<Quarto>() }
    val filteredQuartos = remember { mutableStateListOf<Quarto>() }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadQuartos()
        quartos.clear()
        quartos.addAll(controller.getQuartos())
        isLoading = false
    }

    LaunchedEffect(searchQuery) {
        isLoading = true
        kotlinx.coroutines.delay(500)
        debounceQuery = searchQuery
        isLoading = false
    }

    LaunchedEffect(debounceQuery) {
        filteredQuartos.clear()
        filteredQuartos.addAll(
            quartos.filter { it.descricao.contains(debounceQuery, ignoreCase = true) }
        )
    }
    
    HospitalContent(content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        FontAwesomeIcons.Solid.AngleLeft,
                        contentDescription = "Voltar",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(text = "Menu de Quartos", style = MaterialTheme.typography.h3)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = "Buscar por descrição",
                    modifier = Modifier.padding(16.dp),
                    icon = FontAwesomeIcons.Solid.Search,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isLoading) "Buscando quartos..." else "${filteredQuartos.size} quartos encontrados",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(Modifier.size(8.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = MaterialTheme.shapes.large
                            )
                    )
                    PaginatedDataTable(
                        headerBackgroundColor = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent, shape = MaterialTheme.shapes.large),
                        rowBackgroundColor = { Color.White },
                        footerBackgroundColor = Color.White,
                        columns = listOf(
                            DataColumn {
                                Text(
                                    text = "Descrição",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            DataColumn {
                                Text(
                                    text = "Status",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            DataColumn {
                                Text(
                                    text = "Ala",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        ),
                        state = rememberPaginatedDataTableState(7)
                    ) {
                        filteredQuartos.forEach { quarto ->
                            row {
                                cell { Text(text = quarto.descricao ?: "") }
                                cell { Text(text = quarto.status ?: "") }
                                cell { Text(text = quarto.ala?.descricao ?: "") }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = 51.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.White)
                    )
                    Button(
                        onClick = navigateToRegister,
                        modifier = Modifier.align(Alignment.BottomStart),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text("Cadastrar quarto")
                    }
                }
            }
        }
    )
}
