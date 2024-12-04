package com.luisgmr.ifsc.hospital.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.RegisterUserController
import com.luisgmr.ifsc.hospital.model.Enfermeiro
import com.luisgmr.ifsc.hospital.model.Farmaceutico
import com.luisgmr.ifsc.hospital.model.Medico
import com.luisgmr.ifsc.hospital.model.Usuario

enum class UserType {
    Usuario, Medico, Enfermeiro, Farmaceutico
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterUserScreen(controller: RegisterUserController) {
    var userType by remember { mutableStateOf(UserType.Usuario) }
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
                UserType.Medico -> {
                    OutlinedTextField(
                        value = crm,
                        onValueChange = { crm = it },
                        label = { Text("CRM") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                UserType.Enfermeiro -> {
                    OutlinedTextField(
                        value = cre,
                        onValueChange = { cre = it },
                        label = { Text("CRE") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                UserType.Farmaceutico -> {
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
                    val user = when (userType) {
                        UserType.Usuario ->  {
                            val usuario = Usuario()
                            usuario.nome = nome
                            usuario.nomeSocial = nome
                            usuario.login = login
                            usuario.senha = senha
                            usuario
                        }
                        UserType.Medico -> {
                            val medico = Medico()
                            medico.nomeSocial = nome
                            medico.login = login
                            medico.senha = senha
                            medico.crm = crm
                            medico
                        }
                        UserType.Enfermeiro -> {
                            val enfermeiro = Enfermeiro()
                            enfermeiro.nomeSocial = nome
                            enfermeiro.login = login
                            enfermeiro.senha = senha
                            enfermeiro.cre = cre
                            enfermeiro
                        }
                        UserType.Farmaceutico -> {
                            val farmaceutico = Farmaceutico()
                            farmaceutico.nomeSocial = nome
                            farmaceutico.login = login
                            farmaceutico.senha = senha
                            farmaceutico.cfr = cfr
                            farmaceutico
                        }
                    }
                    val success = controller.registerUser(user)
                    if (success) {
                        println("Usuário registrado com sucesso!")
                    } else {
                        println("Erro ao registrar usuário!")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }
    }
}


fun registerUser(
    userType: UserType,
    nome: String,
    login: String,
    senha: String,
    crm: String = "",
    cre: String = "",
    cfr: String = ""
) {
    when (userType) {
        UserType.Usuario -> {
            println("Registrando usuário: $nome, Login: $login")
            // Chamada para inserir no banco
        }
        UserType.Medico -> {
            println("Registrando médico: $nome, CRM: $crm, Login: $login")
            // Chamada para inserir no banco
        }
        UserType.Enfermeiro -> {
            println("Registrando enfermeiro: $nome, CRE: $cre, Login: $login")
            // Chamada para inserir no banco
        }
        UserType.Farmaceutico -> {
            println("Registrando farmacêutico: $nome, CFR: $cfr, Login: $login")
            // Chamada para inserir no banco
        }
    }
}