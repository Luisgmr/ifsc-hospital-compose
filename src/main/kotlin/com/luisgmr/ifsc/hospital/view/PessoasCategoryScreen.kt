package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
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
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.model.*
import com.luisgmr.ifsc.hospital.navigation.NavController
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.Search

enum class SelectedButton {
    NOME, CPF
}

@Composable
fun PessoasCategoryScreen(
    controller: PessoasCategoryController = PessoasCategoryController(),
    pessoaType: PessoaType,
    navController: NavController,
    onBack: () -> Unit
) {
    val pessoas = remember { mutableStateListOf<Pessoa>() }
    val filteredPessoas = remember { mutableStateListOf<Pessoa>() }
    val selectedButton = remember { mutableStateOf(SelectedButton.NOME) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var debounceQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        isLoading = true
        controller.loadPessoas(pessoaType)
        pessoas.clear()
        pessoas.addAll(
            when (pessoaType) {
                PessoaType.PACIENTE -> ClasseDados.getInstance().pacientes
                PessoaType.MEDICO -> ClasseDados.getInstance().medicos
                PessoaType.ENFERMEIRO -> ClasseDados.getInstance().enfermeiros
                PessoaType.FARMACEUTICO -> ClasseDados.getInstance().farmaceuticos
                PessoaType.USUARIO -> ClasseDados.getInstance().usuarios
            }
        )
        isLoading = false
    }

    LaunchedEffect(searchQuery) {
        isLoading = true
        kotlinx.coroutines.delay(500)
        debounceQuery = searchQuery
        isLoading = false
    }

    LaunchedEffect(debounceQuery, selectedButton.value) {
        filteredPessoas.clear()
        filteredPessoas.addAll(
            pessoas.filter { paciente ->
                when (selectedButton.value) {
                    SelectedButton.NOME -> paciente.nome?.contains(debounceQuery, ignoreCase = true) == true
                    SelectedButton.CPF -> paciente.cpfCnpj?.contains(debounceQuery, ignoreCase = true) == true
                }
            }
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
                    text = "Menu de ${pessoaType.pluralName.lowercase()}",
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
                    SelectableButton("Nome", { selectedButton.value = SelectedButton.NOME }, selectedButton.value == SelectedButton.NOME)
                    SelectableButton("CPF", { selectedButton.value = SelectedButton.CPF }, selectedButton.value == SelectedButton.CPF)
                }
                var text by remember { mutableStateOf("") }

                HospitalTextField(
                    text = searchQuery,
                    onTextChange = { searchQuery = it },
                    placeholder = when (selectedButton.value) {
                        SelectedButton.NOME -> { "Buscar por nome do ${pessoaType.displayName.lowercase()}" }
                        SelectedButton.CPF -> { "Buscar por CPF do ${pessoaType.displayName.lowercase()}" }
                    },
                    modifier = Modifier.padding(16.dp),
                    icon = FontAwesomeIcons.Solid.Search,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isLoading) {
                        "Buscando ${pessoaType.pluralName.lowercase()}..."
                    } else {
                        "${filteredPessoas.size} ${pessoaType.pluralName.lowercase()} encontrados"
                    },
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
                                    text = when (pessoaType) {
                                        PessoaType.PACIENTE -> "Tipo sanguíneo"
                                        PessoaType.MEDICO -> "CRM"
                                        PessoaType.ENFERMEIRO -> "CRE"
                                        PessoaType.FARMACEUTICO -> "CFR"
                                        PessoaType.USUARIO -> "Email"
                                    },
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                            DataColumn {
                                Text(
                                    text = when (pessoaType) {
                                        PessoaType.PACIENTE -> "Sexo"
                                        else -> {"Login"}
                                    },
                                    modifier = Modifier.offset(x = 16.dp),
                                    color = Color.White
                                )
                            },
                        ),
                        state = rememberPaginatedDataTableState(7),
                    ) {
                        filteredPessoas.forEach { pessoa ->
                            row {
                                cell {
                                    Text(
                                        text = pessoa.nome ?: "",
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                cell { Text(pessoa.cpfCnpj ?: "") }
                                cell {
                                    Text(when (pessoaType) {
                                        PessoaType.PACIENTE -> {(pessoa as Paciente).tipoSanguineo}
                                        PessoaType.MEDICO -> {(pessoa as Medico).crm}
                                        PessoaType.ENFERMEIRO -> {(pessoa as Enfermeiro).cre}
                                        PessoaType.FARMACEUTICO -> {(pessoa as Farmaceutico).cfr}
                                        PessoaType.USUARIO -> {(pessoas as Usuario).login}
                                    } ?: "")
                                }
                                cell {
                                    Text(when (pessoaType) {
                                        PessoaType.PACIENTE -> {(pessoa as Paciente).sexo}
                                        else -> {pessoa.email}
                                    } ?: "")
                                }
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
                        onClick = {
                            navController.navigate(Screen.CADASTRO_PESSOA)
                        },
                        modifier = Modifier.align(Alignment.BottomStart),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text("Cadastrar ${pessoaType.displayName.lowercase()}")
                    }
                }
            }
        })
    }
}