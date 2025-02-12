package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import com.luisgmr.ifsc.hospital.controller.AlaController
import com.luisgmr.ifsc.hospital.controller.QuartoController
import com.luisgmr.ifsc.hospital.model.Ala
import com.luisgmr.ifsc.hospital.model.Quarto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuartoRegisterScreen(
    quartoController: QuartoController = QuartoController(),
    alaController: AlaController = AlaController(),
    onBack: () -> Unit
) {
    var descricao by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Disponível") }
    var selectedAla by remember { mutableStateOf<Ala?>(null) }
    var alaDropdownExpanded by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    var alasList by remember { mutableStateOf(emptyList<Ala>()) }
    LaunchedEffect(Unit) {
        alaController.loadAlas()
        alasList = alaController.getAlas()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Quarto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            FontAwesomeIcons.Solid.AngleLeft,
                            contentDescription = "Voltar",
                            modifier = Modifier.size(24.dp)
                        )
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
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                isError = isError && descricao.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = status,
                onValueChange = { status = it },
                label = { Text("Status") },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = alaDropdownExpanded,
                onExpandedChange = { alaDropdownExpanded = !alaDropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedAla?.descricao ?: "Selecione uma ala",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Ala") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = alaDropdownExpanded)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = alaDropdownExpanded,
                    onDismissRequest = { alaDropdownExpanded = false }
                ) {
                    alasList.forEach { ala ->
                        DropdownMenuItem(onClick = {
                            selectedAla = ala
                            alaDropdownExpanded = false
                        }) {
                            Text(text = ala.descricao ?: "")
                        }
                    }
                }
            }

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(
                onClick = {
                    isError = false
                    message = ""
                    if (descricao.isEmpty() || selectedAla == null) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        val quarto = Quarto().apply {
                            this.descricao = descricao
                            this.status = status
                            this.ala = selectedAla
                        }
                        try {
                            quartoController.saveQuarto(quarto)
                            message = "Quarto salvo com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar o quarto: ${e.message}. Tente novamente."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Quarto")
            }
        }
    }
}