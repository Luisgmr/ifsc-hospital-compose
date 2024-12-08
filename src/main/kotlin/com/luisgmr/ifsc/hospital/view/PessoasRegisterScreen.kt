package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalOutlinedTextField
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.model.*
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.AngleRight
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
    HospitalContent(
        verticalArrangement = spacedBy(8.dp),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onBack() }) {
                    Icon(FontAwesomeIcons.Solid.AngleLeft, contentDescription = "Voltar", modifier = Modifier.size(24.dp))
                }
                Row {
                    Text("Cadastrando um ", style = MaterialTheme.typography.h3)
                    Text(pessoaType.displayName.lowercase(), style = MaterialTheme.typography.h3, color = MaterialTheme.colors.primary)
                }
            }
            Text("Dados pessoais")
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(nome, {nome = it}, "Nome", Modifier.weight(1f))
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HospitalOutlinedTextField(cpf, {cpf = it}, "CPF", Modifier.weight(1f))
                    HospitalOutlinedTextField(rg, {rg = it}, "RG", Modifier.weight(1f))
                }
            }
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(email, {email = it}, "Email", Modifier.weight(1f))
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HospitalOutlinedTextField(fone1, {fone1 = it}, "Fone 1", Modifier.weight(1f))
                    HospitalOutlinedTextField(fone2, {fone2 = it}, "Fone 2", Modifier.weight(1f))
                }
            }
    })
}
