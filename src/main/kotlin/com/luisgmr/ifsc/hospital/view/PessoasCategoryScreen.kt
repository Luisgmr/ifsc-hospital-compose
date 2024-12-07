package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalTextField
import com.luisgmr.ifsc.hospital.components.SelectableButton
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.model.ClasseDados
import com.luisgmr.ifsc.hospital.model.Paciente
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.AngleRight
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButton {
    TODOS, MASCULINO, FEMININO
}

@Composable
fun PessoasCategoryScreen(
    controller: PessoasCategoryController = PessoasCategoryController(),
    category: String,
    onBack: () -> Unit
) {
    val pacientes = remember { mutableStateListOf<Paciente>() }
    val filteredPacientes = remember { mutableStateListOf<Paciente>() }
    val selectedButton = remember { mutableStateOf(SelectedButton.TODOS) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") } // Texto de pesquisa

    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadPacientes()
        pacientes.clear()
        pacientes.addAll(ClasseDados.getInstance().pacientes)
        isLoading = false
    }

    LaunchedEffect(searchQuery) {
        filteredPacientes.clear()
        filteredPacientes.addAll(
            pacientes.filter { it.nome?.contains(searchQuery, ignoreCase = true) == true }
        )
    }

    HospitalTheme {
        HospitalContent(content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Back", Modifier.size(24.dp))
                }
                Text(
                    text = "Menu de pacientes",
                    style = MaterialTheme.typography.h3
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableButton("Todos", { selectedButton.value = SelectedButton.TODOS }, selectedButton.value == SelectedButton.TODOS)
                    SelectableButton("Masculino", { selectedButton.value = SelectedButton.MASCULINO }, selectedButton.value == SelectedButton.MASCULINO)
                    SelectableButton("Feminino", { selectedButton.value = SelectedButton.FEMININO }, selectedButton.value == SelectedButton.FEMININO)
                }
                var text by remember { mutableStateOf("") }

                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = "Pesquisar paciente",
                    modifier = Modifier.padding(16.dp),
                    icon = FontAwesomeIcons.Solid.Search,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total de pacientes: ${filteredPacientes.size}",
                    style = MaterialTheme.typography.caption,
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
                Box() {
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
                                    text = "Tipo sanguíneo",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                            DataColumn {
                                Text(
                                    text = "Sexo",
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                        ),
                        state = rememberPaginatedDataTableState(7),
                    ) {
                        filteredPacientes.forEach { paciente ->
                            row {
                                cell {
                                    Text(
                                        text = paciente.nome ?: "",
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                cell { Text(paciente.cpfCnpj ?: "") }
                                cell { Text(paciente.tipoSanguineo ?: "") }
                                cell { Text(paciente.sexo ?: "") }
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
                        onClick = {},
                        modifier = Modifier.align(Alignment.BottomStart),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text("Cadastrar paciente")
                    }
                }
            }
        })
    }
}