package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.model.*
import java.time.LocalDate

@Composable
fun CadastroPessoaScreen(
    pessoaType: PessoaType,
    controller: PessoasCategoryController,
    onBack: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var fone1 by remember { mutableStateOf("") }
    var fone2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf(LocalDate.now()) }
    var endereco by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var tipoSanguineo by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var crm by remember { mutableStateOf("") }
    var cre by remember { mutableStateOf("") }
    var cfr by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar ${pessoaType.displayName}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
                OutlinedTextField(value = cpf, onValueChange = { cpf = it }, label = { Text("CPF") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

                when (pessoaType) {
                    PessoaType.PACIENTE -> {
                        OutlinedTextField(value = tipoSanguineo, onValueChange = { tipoSanguineo = it }, label = { Text("Tipo Sanguíneo") })
                        OutlinedTextField(value = sexo, onValueChange = { sexo = it }, label = { Text("Sexo") })
                    }
                    PessoaType.MEDICO -> {
                        OutlinedTextField(value = crm, onValueChange = { crm = it }, label = { Text("CRM") })
                        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Login") })
                        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })
                    }
                    PessoaType.ENFERMEIRO -> {
                        OutlinedTextField(value = cre, onValueChange = { cre = it }, label = { Text("CRE") })
                        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Login") })
                        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })
                    }
                    PessoaType.FARMACEUTICO -> {
                        OutlinedTextField(value = cfr, onValueChange = { cfr = it }, label = { Text("CFR") })
                        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Login") })
                        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })
                    }
                    PessoaType.USUARIO -> {
                        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Login") })
                        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })
                    }
                }

                Spacer(Modifier.weight(1f))

                Button(onClick = {
                    val pessoa = when (pessoaType) {
                        PessoaType.PACIENTE -> Paciente(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, bairro, logradouro, complemento, tipoSanguineo, sexo, nome, dataNascimento)
                        PessoaType.MEDICO -> Medico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, bairro, logradouro, complemento, crm, senha, login, nome)
                        PessoaType.ENFERMEIRO -> Enfermeiro(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, bairro, logradouro, complemento, cre, senha, login, nome)
                        PessoaType.FARMACEUTICO -> Farmaceutico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, bairro, logradouro, complemento, cfr, senha, login, nome)
                        PessoaType.USUARIO -> Usuario(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, bairro, logradouro, complemento, login, senha, nome)
                    }
                    controller.savePessoa(pessoa)
                    onBack()
                }) {
                    Text("Salvar")
                }
            }
        }
    )
}
