package com.luisgmr.ifsc.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.luisgmr.ifsc.hospital.components.ConnectDatabaseButton
import com.luisgmr.ifsc.hospital.components.HospitalNavigationRailItem
import com.luisgmr.ifsc.hospital.controller.AcompanhanteController
import com.luisgmr.ifsc.hospital.controller.AlaController
import com.luisgmr.ifsc.hospital.controller.ExameController
import com.luisgmr.ifsc.hospital.controller.LaboratorioController
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.controller.RegisterUserController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.model.Pessoa
import com.luisgmr.ifsc.hospital.screens.RegisterUserScreen
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import com.luisgmr.ifsc.hospital.navigation.NavigationHost
import com.luisgmr.ifsc.hospital.navigation.composable
import com.luisgmr.ifsc.hospital.navigation.rememberNavController
import com.luisgmr.ifsc.hospital.view.*
import java.awt.Dimension


enum class Screen {
    HOME, PESSOAS, BUSCAS, USUARIO, REGISTRO_USUARIO,
    PACIENTES, MEDICOS, ENFERMEIROS, FARMACEUTICOS, USUARIOS,
    CADASTRO_PESSOA, EDITAR_PESSOA, ACOMPANHANTES, CADASTRO_ACOMPANHANTE, ALAS,
    CADASTRO_ALA, EXAMES, CADASTRO_EXAME, LABORATORIOS, CADASTRO_LABORATORIO

}

@Composable
fun App() {
    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.HOME)
    val currentScreen by remember { navController.currentScreen }

    HospitalTheme {
        Scaffold(
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    NavigationRail(
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White,
                    ) {
                        Column(
                            modifier = Modifier.weight(1.0f, true),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.HOME,
                                    onClick = { navController.navigate(Screen.HOME) },
                                    icon = { Icon(FontAwesomeIcons.Solid.AddressCard, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Home") }
                                )
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.PESSOAS,
                                    onClick = { navController.navigate(Screen.PESSOAS) },
                                    icon = { Icon(FontAwesomeIcons.Solid.HospitalUser, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Pessoas") }
                                )
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.ACOMPANHANTES,
                                    onClick = { navController.navigate(Screen.ACOMPANHANTES) },
                                    icon = { Icon(FontAwesomeIcons.Solid.Users, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Acompanhantes") }
                                )
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.ALAS,
                                    onClick = { navController.navigate(Screen.ALAS) },
                                    icon = { Icon(FontAwesomeIcons.Solid.Hospital, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Alas") }
                                )
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.EXAMES,
                                    onClick = { navController.navigate(Screen.EXAMES) },
                                    icon = { Icon(FontAwesomeIcons.Solid.FileMedical, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Exames") }
                                )
                                HospitalNavigationRailItem(
                                    selected = currentScreen == Screen.LABORATORIOS,
                                    onClick = { navController.navigate(Screen.LABORATORIOS) },
                                    icon = { Icon(FontAwesomeIcons.Solid.Flask, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Laboratórios") }
                                )
                            }
                            HospitalNavigationRailItem(
                                selected = currentScreen == Screen.REGISTRO_USUARIO,
                                onClick = { navController.navigate(Screen.REGISTRO_USUARIO) },
                                icon = { Icon(FontAwesomeIcons.Solid.AngleRight, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                label = { Text("Registrar") }
                            )
                        }
                    }
                }

                NavigationHost(navController = navController) {
                    composable(Screen.HOME) { HomeScreen(navController) }
                    composable(Screen.REGISTRO_USUARIO) { RegisterUserScreen(RegisterUserController()) }
                    composable(Screen.PESSOAS) { PessoasScreen(navController) }
                    composable(Screen.PACIENTES) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.PACIENTE, navController, onBack = { navController.navigateBack() }) }
                    composable(Screen.MEDICOS) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.MEDICO, navController, onBack = { navController.navigateBack() }) }
                    composable(Screen.ENFERMEIROS) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.ENFERMEIRO, navController, onBack = { navController.navigateBack() }) }
                    composable(Screen.FARMACEUTICOS) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.FARMACEUTICO, navController, onBack = { navController.navigateBack() }) }
                    composable(Screen.USUARIOS) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.USUARIO, navController, onBack = { navController.navigateBack() }) }
                    composable(Screen.CADASTRO_PESSOA) {
                        val pessoaType = navController.getArgumentsForCurrentScreen()?.get("pessoaType") as? PessoaType
                            ?: PessoaType.PACIENTE
                        CadastroPessoaScreen(
                            pessoaType = pessoaType,
                            controller = PessoasCategoryController(),
                            onBack = { navController.navigateBack() }
                        )
                    }
                    composable(Screen.EDITAR_PESSOA) {
                        val pessoaType = navController.getArgumentsForCurrentScreen()?.get("pessoaType") as? PessoaType
                            ?: PessoaType.PACIENTE
                        val pessoa = navController.getArgumentsForCurrentScreen()?.get("pessoa") as? Pessoa
                            ?: Pessoa()
                        PessoasEditScreen(
                            pessoa,
                            pessoaType = pessoaType,
                            controller = PessoasCategoryController(),
                            onBack = { navController.navigateBack() }
                        )
                    }
                    composable(Screen.ACOMPANHANTES) { AcompanhanteScreen(AcompanhanteController(),navController, { navController.navigateBack() }) }
                    composable(Screen.CADASTRO_ACOMPANHANTE) { AcompanhanteRegisterScreen(AcompanhanteController(), onBack = { navController.navigateBack() }) }
                    composable(Screen.ALAS) { AlaScreen(AlaController(),navController, { navController.navigateBack() }) }
                    composable(Screen.CADASTRO_ALA) { AlaRegisterScreen(controller = AlaController(), onBack = { navController.navigateBack() })}
                    composable(Screen.EXAMES) { ExameScreen(ExameController(), navController, { navController.navigateBack() }) }
                    composable(Screen.CADASTRO_EXAME) { ExameRegisterScreen(controller = ExameController(), onBack = { navController.navigateBack() })}
                    composable(Screen.LABORATORIOS) { LaboratorioScreen(LaboratorioController(), navController, { navController.navigateBack() }) }
                    composable(Screen.CADASTRO_LABORATORIO) { LaboratorioRegisterScreen(controller = LaboratorioController(), onBack = { navController.navigateBack() }
                    )
                    }
                }.build()
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        resizable = true
    ) {
        this.window.minimumSize = Dimension(1000, 750)
        App()
    }
}

@Composable
fun ConsultasScreen() {
    Text("Consulte informações.")
}

@Composable
fun UsuarioScreen() {
    Text("Perfil do Usuário.")
}
