package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.AlaController
import com.luisgmr.ifsc.hospital.model.Ala
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlaRegisterScreen(
    controller: AlaController = AlaController(),
    onBack: () -> Unit
) {
    var descricao by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Urgência") } // Default value
    var expanded by remember { mutableStateOf(false) } // For ExposedDropdownMenu
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Ala") },
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo Descrição
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                isError = isError && descricao.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Status (Dropdown com ExposedDropdownMenu)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = status,
                    onValueChange = {},
                    label = { Text("Status") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Críticas", "Semicríticas", "Não Críticas").forEach { option ->
                        DropdownMenuItem(onClick = {
                            status = option
                            expanded = false // Fecha o menu após a seleção
                        }) {
                            Text(option)
                        }
                    }
                }
            }

            // Exibir Mensagem de Sucesso ou Erro
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Botão para salvar a Ala
            Button(
                onClick = {
                    isError = false
                    message = ""
                    // Validação de campos
                    if (descricao.isEmpty()) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        // Criação do objeto Ala
                        val ala = Ala(
                            descricao,
                            status
                        )

                        try {
                            // Chama o método de salvar no controller
                            controller.saveAla(ala)
                            message = "Ala salva com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar a Ala: ${e.message}. Tente novamente."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Ala")
            }
        }
    }
}
