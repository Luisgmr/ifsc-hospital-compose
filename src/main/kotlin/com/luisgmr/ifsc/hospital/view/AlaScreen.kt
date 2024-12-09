package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.Screen
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalTextField
import com.luisgmr.ifsc.hospital.components.SelectableButton
import com.luisgmr.ifsc.hospital.controller.AlaController
import com.luisgmr.ifsc.hospital.model.Ala
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search


@Composable
fun AlaScreen(
    controller: AlaController = AlaController(),
    navController: NavController,
    onBack: () -> Unit
) {
    val alas = remember { mutableStateListOf<Ala>() }
    val filteredAlas = remember { mutableStateListOf<Ala>() }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    // Load alas when the screen starts
    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadAlas()
        alas.clear()
        alas.addAll(controller.getAlas())
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
    LaunchedEffect(debounceQuery) {
        filteredAlas.clear()
        filteredAlas.addAll(
            alas.filter { ala ->
                ala.descricao.contains(debounceQuery, ignoreCase = true)
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
                    text = "Menu de Alas",
                    style = MaterialTheme.typography.h3
                )
            }

            // Filter Options
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

            // Status Message
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isLoading) {
                        "Buscando alas..."
                    } else {
                        "${filteredAlas.size} alas encontradas"
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
                        ),
                        state = rememberPaginatedDataTableState(7),
                    ) {
                        filteredAlas.forEach { ala ->
                            row {
                                cell {
                                    Text(
                                        text = ala.descricao ?: "",
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                cell { Text(ala.status ?: "") }
                            }
                        }
                    }

                // Linha separadora (opcional)
                    Box(
                        modifier = Modifier
                            .offset(y = 51.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.White)
                    )

                // Botão de cadastrar
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Screen.CADASTRO_ALA)
                            },
                            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text("Cadastrar Ala")
                        }
                    }
                }
            }
        })
    }
}
