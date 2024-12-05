package com.luisgmr.ifsc.hospital.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.RegisterUserController
import com.luisgmr.ifsc.hospital.enums.UserType

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterUserScreen(controller: RegisterUserController) {
    var userType by remember { mutableStateOf(UserType.USUARIO) }
    var expanded by remember { mutableStateOf(false) }
    var nome by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var crm by remember { mutableStateOf("") }
    var cre by remember { mutableStateOf("") }
    var cfr by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registro de Usuário") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tipo de usuário com ExposedDropdownMenu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = userType.name,
                    onValueChange = {},
                    label = { Text("Tipo de Usuário") },
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
                    UserType.values().forEach { type ->
                        DropdownMenuItem(onClick = {
                            userType = type
                            expanded = false // Fecha o menu após a seleção
                        }) {
                            Text(type.name)
                        }
                    }
                }
            }

            // Campo Nome
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Login
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Login") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Senha
            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            // Campos Específicos
            when (userType) {
                UserType.MEDICO -> {
                    OutlinedTextField(
                        value = crm,
                        onValueChange = { crm = it },
                        label = { Text("CRM") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                UserType.ENFERMEIRO -> {
                    OutlinedTextField(
                        value = cre,
                        onValueChange = { cre = it },
                        label = { Text("CRE") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                UserType.FARMACEUTICO -> {
                    OutlinedTextField(
                        value = cfr,
                        onValueChange = { cfr = it },
                        label = { Text("CFR") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                else -> { /* Nenhum campo adicional */ }
            }

            Button(
                onClick = {
                    controller.registerUser(userType, nome, login, senha, crm, cre, cfr)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }
    }
}