package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.ExameController
import com.luisgmr.ifsc.hospital.model.Exame
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExameRegisterScreen(
    controller: ExameController = ExameController(),
    onBack: () -> Unit
) {
    var tituloExame by remember { mutableStateOf("") }
    var tipoExame by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Análise") } // Default value
    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Exame") },
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
            // Campo Título do Exame
            OutlinedTextField(
                value = tituloExame,
                onValueChange = { tituloExame = it },
                label = { Text("Título do Exame") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Tipo de Exame
            OutlinedTextField(
                value = tipoExame,
                onValueChange = { tipoExame = it },
                label = { Text("Tipo de Exame") },
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
                    label = { Text("Status do Exame") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Pré-análise", "Em análise", "Pós-análise", "Positivo", "Negativo").forEach { option ->
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

            // Botão para salvar o exame
            Button(
                onClick = {
                    isError = false
                    message = ""
                    if (tituloExame.isEmpty() || tipoExame.isEmpty()) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        val exame = Exame(
                            tituloExame,
                            tipoExame,
                            status
                        )
                        try {
                            controller.saveExame(exame)
                            message = "Exame salvo com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar o exame: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Exame")
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
