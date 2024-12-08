package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.AcompanhanteController
import com.luisgmr.ifsc.hospital.model.Acompanhante
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft

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
    var status by remember { mutableStateOf("") }
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo Nome
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                isError = isError && nome.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Grau de Parentesco
            OutlinedTextField(
                value = grauParentesco,
                onValueChange = { grauParentesco = it },
                label = { Text("Grau de Parentesco") },
                isError = isError && grauParentesco.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo CPF
            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("CPF") },
                isError = isError && cpf.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Telefone
            OutlinedTextField(
                value = fone,
                onValueChange = { fone = it },
                label = { Text("Telefone") },
                isError = isError && fone.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = isError && email.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Status
            OutlinedTextField(
                value = status,
                onValueChange = { status = it },
                label = { Text("Status") },
                isError = isError && status.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            // Exibir Mensagem de Sucesso ou Erro
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Botão para salvar o acompanhante
            Button(
                onClick = {
                    isError = false
                    message = ""
                    // Validação de campos
                    if (nome.isEmpty() || grauParentesco.isEmpty() || cpf.isEmpty() || fone.isEmpty() || email.isEmpty() || status.isEmpty()) {
                        isError = true
                        message = "Por favor, preencha todos os campos obrigatórios."
                    } else {
                        // Criação do objeto Acompanhante
                        println("Valores do formulário:")
                        println("Nome: $nome, Grau de Parentesco: $grauParentesco, CPF: $cpf, Fone: $fone, Email: $email, Status: $status")

                        val acompanhante = Acompanhante(
                            nome,
                            grauParentesco,
                            cpf,
                            fone,
                            email,
                            status
                        )

                        try {
                            // Chama o método de salvar no controller
                            controller.saveAcompanhante(acompanhante)
                            message = "Acompanhante salvo com sucesso!"
                        } catch (e: Exception) {
                            isError = true
                            message = "Erro ao salvar o acompanhante: ${e.message}. Tente novamente."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Acompanhante")
            }
        }
    }
}
