package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.LaboratorioController
import com.luisgmr.ifsc.hospital.model.Laboratorio
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaboratorioRegisterScreen(
    controller: LaboratorioController = LaboratorioController(),
    onBack: () -> Unit
) {
    var nomeFantasia by remember { mutableStateOf("") }
    var contato by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Ativo") } // Default value
    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Laboratório") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Voltar", Modifier.size(24.dp))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo Nome Fantasia
            OutlinedTextField(
                value = nomeFantasia,
                onValueChange = { nomeFantasia = it },
                label = { Text("Nome Fantasia") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Contato
            OutlinedTextField(
                value = contato,
                onValueChange = { contato = it },
                label = { Text("Contato") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Status com Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = status,
                    onValueChange = {},
                    label = { Text("Status") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Ativo", "Inativo").forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                status = option
                                expanded = false
                            }
                        ) {
                            Text(option)
                        }
                    }
                }
            }

            // Botão para salvar o laboratório
            Button(
                onClick = {
                    isError = false
                    message = ""
                    if (nomeFantasia.isEmpty() || contato.isEmpty()) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        val laboratorio = Laboratorio(
                            nomeFantasia,
                            contato,
                            status
                        )
                        try {
                            controller.saveLaboratorio(laboratorio)
                            message = "Laboratório salvo com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar o laboratório: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Laboratório")
            }

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
