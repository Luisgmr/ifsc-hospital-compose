package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.AcompanhanteController
import com.luisgmr.ifsc.hospital.model.Acompanhante
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AcompanhanteRegisterScreen(
    controller: AcompanhanteController = AcompanhanteController(),
    onBack: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var grauParentesco by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var fone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Ativo") } // Default value
    var expanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Acompanhante") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Back", Modifier.size(24.dp))
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
            // Campo Nome
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Grau de Parentesco
            OutlinedTextField(
                value = grauParentesco,
                onValueChange = { grauParentesco = it },
                label = { Text("Grau de Parentesco") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo CPF
            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("CPF") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Telefone
            OutlinedTextField(
                value = fone,
                onValueChange = { fone = it },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
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

            // Botão para salvar o acompanhante
            Button(
                onClick = {
                    isError = false
                    message = ""
                    if (nome.isEmpty() || grauParentesco.isEmpty() || cpf.isEmpty() || fone.isEmpty() || email.isEmpty()) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        val acompanhante = Acompanhante(
                            nome,
                            grauParentesco,
                            cpf,
                            fone,
                            email,
                            status
                        )
                        try {
                            controller.saveAcompanhante(acompanhante)
                            message = "Acompanhante salvo com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar o acompanhante: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Acompanhante")
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
