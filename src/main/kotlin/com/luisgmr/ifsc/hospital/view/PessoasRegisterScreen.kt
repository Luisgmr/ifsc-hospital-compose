package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.components.HospitalOutlinedTextField
import com.luisgmr.ifsc.hospital.components.HospitalSelect
import com.luisgmr.ifsc.hospital.components.InputType
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.enums.Sexo
import com.luisgmr.ifsc.hospital.enums.TipoSanguineo
import com.luisgmr.ifsc.hospital.enums.UF
import com.luisgmr.ifsc.hospital.model.*
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CadastroPessoaScreen(
    pessoaType: PessoaType,
    controller: PessoasCategoryController,
    onBack: () -> Unit
) {
    val tiposSanguineos = TipoSanguineo.entries.map{it.displayName}
    val sexos = Sexo.entries.map{it.displayName}
    val estados = UF.entries.map{it.sigla}

    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var fone1 by remember { mutableStateOf("") }
    var fone2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var uf by remember { mutableStateOf("") }
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
                    HospitalOutlinedTextField(cpf, {cpf = it}, "CPF", Modifier.weight(1f), InputType.CPF)
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
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(cep, {cep = it}, "CEP", Modifier.weight(0.15f))
                HospitalOutlinedTextField(bairro, {bairro = it}, "Bairro", Modifier.weight(0.25f))
                HospitalOutlinedTextField(cidade, {cidade = it}, "Cidade", Modifier.weight(0.5f))
                HospitalOutlinedTextField(uf, {uf = it}, "UF", Modifier.weight(0.1f), maxLength = 2)
            }
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(logradouro, {logradouro = it}, "Logradouro", Modifier.weight(0.5f))
                HospitalOutlinedTextField(endereco, {endereco = it}, "Endereço", Modifier.weight(0.25f))
                HospitalOutlinedTextField(complemento, {complemento = it}, "Complemento", Modifier.weight(0.25f))
            }
            Spacer(Modifier.size(4.dp))
            Text("Dados específicos do ${pessoaType.displayName.lowercase()}")
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                when (pessoaType) {
                    PessoaType.PACIENTE -> {
                        HospitalSelect(label = "Tipo sanguíneo", options = tiposSanguineos, selectedValue = tipoSanguineo, onValueChange = { tipoSanguineo = it }, placeholder = "Selecione o tipo sanguíneo")
                        HospitalSelect(label = "Sexo", options = sexos, selectedValue = sexo, onValueChange = { sexo = it }, placeholder = "Selecione o sexo")
                        HospitalOutlinedTextField(dataNascimento, {dataNascimento = it}, "Data de nascimento", Modifier.weight(1f), InputType.DATE, "dd/MM/AAAA")
                    }
                    PessoaType.MEDICO -> {
                        HospitalOutlinedTextField(crm, {crm = it}, "CRM", Modifier.weight(1f))
                        HospitalOutlinedTextField(login, {login = it}, "Login", Modifier.weight(1f))
                        HospitalOutlinedTextField(senha, {senha = it}, "Senha", Modifier.weight(1f), InputType.PASSWORD)
                    }
                    PessoaType.ENFERMEIRO -> {
                        HospitalOutlinedTextField(cre, {cre = it}, "CRE", Modifier.weight(1f))
                        HospitalOutlinedTextField(login, {login = it}, "Login", Modifier.weight(1f))
                        HospitalOutlinedTextField(senha, {senha = it}, "Senha", Modifier.weight(1f), InputType.PASSWORD)
                    }
                    PessoaType.FARMACEUTICO -> {
                        HospitalOutlinedTextField(cfr, {cfr = it}, "CFR", Modifier.weight(1f))
                        HospitalOutlinedTextField(login, {login = it}, "Login", Modifier.weight(1f))
                        HospitalOutlinedTextField(senha, {senha = it}, "Senha", Modifier.weight(1f), InputType.PASSWORD)
                    }
                    PessoaType.USUARIO -> {
                        HospitalOutlinedTextField(login, {login = it}, "Login", Modifier.weight(1f))
                        HospitalOutlinedTextField(senha, {senha = it}, "Senha", Modifier.weight(1f), InputType.PASSWORD)
                    }
                }
            }
            Button(
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 24.dp),
                onClick = {
                    when (pessoaType) {
                        PessoaType.PACIENTE -> {
                            val paciente = Paciente(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                tipoSanguineo,
                                sexo,
                                nome,
                                 LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            )
                            controller.savePessoa(paciente)
                            onBack()
                        }
                        PessoaType.MEDICO -> {
                            val medico = Medico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                crm,
                                senha,
                                login,
                                nome
                            )
                            controller.savePessoa(medico)
                            onBack()
                        }
                        PessoaType.ENFERMEIRO -> {
                            val enfermeiro = Enfermeiro(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                cre,
                                senha,
                                login,
                                nome
                            )
                            controller.savePessoa(enfermeiro)
                            onBack()
                        }
                        PessoaType.FARMACEUTICO -> {
                            val farmaceutico = Farmaceutico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                cfr,
                                senha,
                                login,
                                nome
                            )
                            controller.savePessoa(farmaceutico)
                            onBack()
                        }
                        PessoaType.USUARIO -> {
                            val usuario = Usuario(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                login,
                                senha,
                                nome
                            )
                            controller.savePessoa(usuario)
                            onBack()
                        }
                    }
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Cadastrar ${pessoaType.displayName.lowercase()}")
            }
    })
}
