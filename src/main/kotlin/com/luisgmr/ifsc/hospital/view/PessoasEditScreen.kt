package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import javax.swing.JOptionPane

@Composable
fun PessoasEditScreen(
    pessoa: Pessoa,
    pessoaType: PessoaType,
    controller: PessoasCategoryController,
    onBack: () -> Unit
) {
    val tiposSanguineos = TipoSanguineo.entries.map{it.displayName}
    val sexos = Sexo.entries.map{it.displayName}
    val estados = UF.entries.map{it.sigla}

    var nome by remember { mutableStateOf(pessoa.nome ?: "") }
    var cpf by remember { mutableStateOf(pessoa.cpfCnpj ?: "") }
    var rg by remember { mutableStateOf(pessoa.rgInscricaoEstadual ?: "") }
    var fone1 by remember { mutableStateOf(pessoa.fone1 ?: "") }
    var fone2 by remember { mutableStateOf(pessoa.fone2 ?: "") }
    var email by remember { mutableStateOf(pessoa.email ?: "") }
    var endereco by remember { mutableStateOf(pessoa.endereco ?: "") }
    var cep by remember { mutableStateOf(pessoa.cep ?: "") }
    var cidade by remember { mutableStateOf(pessoa.cidade ?: "") }
    var uf by remember { mutableStateOf(pessoa.uf ?: "") }
    var logradouro by remember { mutableStateOf(pessoa.logradouro ?: "") }
    var complemento by remember { mutableStateOf(pessoa.complemento ?: "") }
    var bairro by remember { mutableStateOf(pessoa.bairro ?: "") }
    var dataNascimento by remember { mutableStateOf("") }
    var tipoSanguineo by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var crm by remember { mutableStateOf("") }
    var cre by remember { mutableStateOf("") }
    var cfr by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    when (pessoaType) {
        PessoaType.PACIENTE -> {
            val paciente = pessoa as Paciente
            dataNascimento = paciente.dataNascimento?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))!!.replace("/", "") ?: ""
            tipoSanguineo = paciente.tipoSanguineo ?: ""
            sexo = paciente.sexo ?: ""
        }
        PessoaType.MEDICO -> {
            val medico = pessoa as Medico
            crm = medico.crm ?: ""
            login = medico.login ?: ""
            senha = medico.senha ?: ""
        }
        PessoaType.ENFERMEIRO -> {
            val enfermeiro = pessoa as Enfermeiro
            cre = enfermeiro.cre ?: ""
            login = enfermeiro.login ?: ""
            senha = enfermeiro.senha ?: ""
        }
        PessoaType.FARMACEUTICO -> {
            val farmaceutico = pessoa as Farmaceutico
            cfr = farmaceutico.cfr ?: ""
            login = farmaceutico.login ?: ""
            senha = farmaceutico.senha ?: ""
        }
        PessoaType.USUARIO -> {
            val usuario = pessoa as Usuario
            login = usuario.login ?: ""
            senha = usuario.senha ?: ""
        }
    }

    var nomeError by remember { mutableStateOf(false) }
    var cpfError by remember { mutableStateOf(false) }
    var rgError by remember { mutableStateOf(false) }
    var fone1Error by remember { mutableStateOf(false) }
    var fone2Error by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var dataNascimentoError by remember { mutableStateOf(false) }
    var enderecoError by remember { mutableStateOf(false) }
    var cepError by remember { mutableStateOf(false) }
    var cidadeError by remember { mutableStateOf(false) }
    var ufError by remember { mutableStateOf(false) }
    var logradouroError by remember { mutableStateOf(false) }
    var complementoError by remember { mutableStateOf(false) }
    var bairroError by remember { mutableStateOf(false) }
    var tipoSanguineoError by remember { mutableStateOf(false) }
    var sexoError by remember { mutableStateOf(false) }
    var crmError by remember { mutableStateOf(false) }
    var creError by remember { mutableStateOf(false) }
    var cfrError by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }
    var senhaError by remember { mutableStateOf(false) }

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
                    Text("Editando um ", style = MaterialTheme.typography.h3)
                    Text(pessoaType.displayName.lowercase(), style = MaterialTheme.typography.h3, color = MaterialTheme.colors.primary)
                }
            }
            Text("Dados pessoais")
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(nome,"Nome", Modifier.weight(1f),
                    onValueChange = {
                    nome = it
                    nomeError = false
                },
                    isError = nomeError)
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HospitalOutlinedTextField(value = cpf, label = "CPF", fieldType = InputType.CPF, maxLength = 11, modifier = Modifier.weight(1f),
                        onValueChange = {
                            cpf = it
                            cpfError = false
                        },
                        errorMessage = "CPF inválido",
                        isError = cpfError
                    )
                    HospitalOutlinedTextField(rg, "RG", modifier = Modifier.weight(1f),
                        onValueChange = {
                            rg = it
                            rgError = false
                        },
                        errorMessage = "RG inválido",
                        maxLength = 9,
                        isError = rgError)
                }
            }
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(email, "Email", Modifier.weight(1f),
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    errorMessage = "Email inválido",
                    isError = emailError)
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HospitalOutlinedTextField(
                        fone1, "Fone 1", Modifier.weight(1f), fieldType = InputType.PHONE,
                        onValueChange = {
                            fone1 = it
                            fone1Error = false
                        },
                        errorMessage = "Telefone inválido",
                        isError = fone1Error,
                        maxLength = 11
                    )
                    HospitalOutlinedTextField(
                        fone2, "Fone 2", Modifier.weight(1f), fieldType = InputType.PHONE,
                        onValueChange = {
                            fone2 = it
                            fone2Error = false
                        },
                        errorMessage = "Telefone inválido",
                        isError = fone2Error,
                        maxLength = 11
                    )
                }
            }
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(
                    cep, "CEP", Modifier.weight(0.15f), fieldType = InputType.CEP,
                    onValueChange = {
                        cep = it
                        cepError = false
                    },
                    errorMessage = "CEP inválido",
                    maxLength = 8,
                    isError = cepError
                )
                HospitalOutlinedTextField(
                    bairro, "Bairro", Modifier.weight(0.25f),
                    onValueChange = {
                        bairro = it
                        bairroError = false
                    },
                    errorMessage = "Bairro inválido",
                    isError = bairroError
                )
                HospitalOutlinedTextField(
                    cidade, "Cidade", Modifier.weight(0.5f),
                    onValueChange = {
                        cidade = it
                        cidadeError = false
                    },
                    errorMessage = "Cidade inválida",
                    isError = cidadeError
                )
                HospitalOutlinedTextField(
                    uf, "UF", Modifier.weight(0.1f),
                    onValueChange = {
                        uf = it
                        ufError = false
                    },
                    errorMessage = "Inválido",
                    isError = ufError,
                    maxLength = 2
                )
            }
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                HospitalOutlinedTextField(
                    logradouro, "Logradouro", Modifier.weight(0.5f),
                    onValueChange = {
                        logradouro = it
                        logradouroError = false
                    },
                    errorMessage = "Logradouro inválido",
                    isError = logradouroError
                )
                HospitalOutlinedTextField(
                    endereco, "Endereço", Modifier.weight(0.25f),
                    onValueChange = {
                        endereco = it
                        enderecoError = false
                    },
                    errorMessage = "Endereço inválido",
                    isError = enderecoError
                )
                HospitalOutlinedTextField(
                    complemento, "Complemento", Modifier.weight(0.25f),
                    onValueChange = {
                        complemento = it
                        complementoError = false
                    },
                    errorMessage = "Complemento inválido",
                    isError = complementoError
                )
            }
            Spacer(Modifier.size(4.dp))
            Text("Dados específicos do ${pessoaType.displayName.lowercase()}")
            Row(
                horizontalArrangement = spacedBy(8.dp),
            ) {
                when (pessoaType) {
                    PessoaType.PACIENTE -> {
                        HospitalSelect(
                            label = "Tipo sanguíneo",
                            options = tiposSanguineos,
                            selectedValue = tipoSanguineo,
                            onValueChange = { tipoSanguineo = it },
                            placeholder = "Selecione o tipo sanguíneo",
                            isError = tipoSanguineoError,
                            isEnabled = false
                        )
                        HospitalSelect(
                            label = "Sexo",
                            options = sexos,
                            selectedValue = sexo,
                            onValueChange = { sexo = it },
                            placeholder = "Selecione o sexo",
                            isError = sexoError
                        )
                        HospitalOutlinedTextField(
                            dataNascimento, "Data de nascimento", Modifier.weight(1f), maxLength = 8,

                            onValueChange = {
                                dataNascimento = it
                                dataNascimentoError = false
                            },
                            isEnabled = false,
                            fieldType = InputType.DATE,
                            placeholder = "dd/MM/AAAA",
                            errorMessage = "Data inválida",
                            isError = dataNascimentoError,
                            maxLines = 8,
                        )
                    }
                    PessoaType.MEDICO -> {
                        HospitalOutlinedTextField(
                            crm, "CRM", Modifier.weight(1f),
                            onValueChange = {
                                crm = it
                                crmError = false
                            },
                            errorMessage = "CRM inválido",
                            isError = crmError
                        )
                        HospitalOutlinedTextField(
                            login, "Login", Modifier.weight(1f),
                            onValueChange = {
                                login = it
                                loginError = false
                            },
                            errorMessage = "Login inválido",
                            isError = loginError
                        )
                        HospitalOutlinedTextField(
                            senha, "Senha", Modifier.weight(1f),
                            onValueChange = {
                                senha = it
                                senhaError = false
                            },
                            fieldType = InputType.PASSWORD,
                            errorMessage = "Senha inválida",
                            isError = senhaError
                        )
                    }
                    PessoaType.ENFERMEIRO -> {
                        HospitalOutlinedTextField(
                            cre, "CRE", Modifier.weight(1f),
                            onValueChange = {
                                cre = it
                                creError = false
                            },
                            errorMessage = "CRE inválido",
                            isError = creError
                        )
                        HospitalOutlinedTextField(
                            login, "Login", Modifier.weight(1f),
                            onValueChange = {
                                login = it
                                loginError = false
                            },
                            errorMessage = "Login inválido",
                            isError = loginError
                        )
                        HospitalOutlinedTextField(
                            senha, "Senha", Modifier.weight(1f),
                            onValueChange = {
                                senha = it
                                senhaError = false
                            },
                            fieldType = InputType.PASSWORD,
                            errorMessage = "Senha inválida",
                            isError = senhaError
                        )
                    }
                    PessoaType.FARMACEUTICO -> {
                        HospitalOutlinedTextField(
                            cfr, "CFR", Modifier.weight(1f),
                            onValueChange = {
                                cfr = it
                                cfrError = false
                            },
                            errorMessage = "CFR inválido",
                            isError = cfrError
                        )
                        HospitalOutlinedTextField(
                            login, "Login", Modifier.weight(1f),
                            onValueChange = {
                                login = it
                                loginError = false
                            },
                            errorMessage = "Login inválido",
                            isError = loginError
                        )
                        HospitalOutlinedTextField(
                            senha, "Senha", Modifier.weight(1f),
                            onValueChange = {
                                senha = it
                                senhaError = false
                            },
                            fieldType = InputType.PASSWORD,
                            errorMessage = "Senha inválida",
                            isError = senhaError
                        )
                    }
                    PessoaType.USUARIO -> {
                        HospitalOutlinedTextField(
                            login, "Login", Modifier.weight(1f),
                            onValueChange = {
                                login = it
                                loginError = false
                            },
                            errorMessage = "Login inválido",
                            isError = loginError
                        )
                        HospitalOutlinedTextField(
                            senha, "Senha", Modifier.weight(1f),
                            onValueChange = {
                                senha = it
                                senhaError = false
                            },
                            fieldType = InputType.PASSWORD,
                            errorMessage = "Senha inválida",
                            isError = senhaError
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Button(
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 24.dp),
                    onClick = {

                        nomeError = nome.isBlank()
                        cpfError = cpf.length != 11
                        rgError = rg.isBlank()
                        fone1Error = fone1.isBlank()
                        fone2Error = fone2.isNotBlank() && fone2.length < 10 // Opcional, mas se preenchido, deve ser válido
                        emailError = email.isBlank() || !email.contains("@")
                        dataNascimentoError = dataNascimento.isBlank() || parseDateFromRawInput(dataNascimento) == null
                        enderecoError = endereco.isBlank()
                        cepError = cep.length != 8
                        cidadeError = cidade.isBlank()
                        ufError = uf.isBlank() || uf.length != 2
                        logradouroError = logradouro.isBlank()
                        complementoError = complemento.isBlank()
                        bairroError = bairro.isBlank()
                        tipoSanguineoError = tipoSanguineo.isBlank()
                        sexoError = sexo.isBlank()
                        crmError = pessoaType == PessoaType.MEDICO && crm.isBlank()
                        creError = pessoaType == PessoaType.ENFERMEIRO && cre.isBlank()
                        cfrError = pessoaType == PessoaType.FARMACEUTICO && cfr.isBlank()
                        loginError = pessoaType != PessoaType.PACIENTE && login.isBlank()
                        senhaError = pessoaType != PessoaType.PACIENTE && senha.isBlank()

                        val hasErrors = nomeError || cpfError || rgError || fone1Error || fone2Error || emailError || dataNascimentoError ||
                                enderecoError || cepError || cidadeError || ufError || logradouroError || complementoError || bairroError ||
                                tipoSanguineoError || sexoError || crmError || creError || cfrError || loginError || senhaError

                        if (!hasErrors) {
                            when (pessoaType) {
                                PessoaType.PACIENTE -> {
                                    val parsedDate = parseDateFromRawInput(dataNascimento)
                                    if (parsedDate != null) {
                                        val paciente = Paciente(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                            tipoSanguineo,
                                            sexo,
                                            nome,
                                            parsedDate
                                        )
                                        controller.updatePessoa(pessoa.cpfCnpj, paciente, pessoaType)
                                        onBack()
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Data invalida")
                                    }
                                }
                                PessoaType.MEDICO -> {
                                    val medico = Medico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                        crm,
                                        senha,
                                        login,
                                        nome
                                    )
                                    controller.updatePessoa(pessoa.cpfCnpj, medico, pessoaType)
                                    onBack()
                                }
                                PessoaType.ENFERMEIRO -> {
                                    val enfermeiro = Enfermeiro(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                        cre,
                                        senha,
                                        login,
                                        nome
                                    )
                                    controller.updatePessoa(pessoa.cpfCnpj, enfermeiro, pessoaType)
                                    onBack()
                                }
                                PessoaType.FARMACEUTICO -> {
                                    val farmaceutico = Farmaceutico(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                        cfr,
                                        senha,
                                        login,
                                        nome
                                    )
                                    controller.updatePessoa(pessoa.cpfCnpj, farmaceutico, pessoaType)
                                    onBack()
                                }
                                PessoaType.USUARIO -> {
                                    val usuario = Usuario(nome, fone1, fone2, email, cpf, rg, LocalDate.now().toString(), endereco, cep, cidade, uf, bairro, logradouro, complemento,
                                        login,
                                        senha,
                                        nome
                                    )
                                    controller.updatePessoa(pessoa.cpfCnpj, usuario, pessoaType)
                                    onBack()
                                }
                            }
                            JOptionPane.showMessageDialog(null, pessoaType.displayName + " editado com sucesso!")
                        }

                    },
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Salvar ${pessoaType.displayName.lowercase()}")
                }
                Button(
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 24.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                    onClick = {
                        controller.deletePessoa(pessoa.cpfCnpj, pessoaType)
                        JOptionPane.showMessageDialog(null, "$pessoaType excluído com sucesso!")
                        onBack()
                    }
                ) {
                    Text("Excluir ${pessoaType.displayName.lowercase()}")
                }
            }
    })
}

