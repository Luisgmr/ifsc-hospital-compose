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
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.controller.RegisterUserController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.screens.RegisterUserScreen
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.luisgmr.ifsc.hospital.view.PessoasScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import com.luisgmr.ifsc.hospital.navigation.NavigationHost
import com.luisgmr.ifsc.hospital.navigation.composable
import com.luisgmr.ifsc.hospital.navigation.rememberNavController
import com.luisgmr.ifsc.hospital.view.PessoasCategoryScreen
import java.awt.Dimension


enum class Screen {
    HOME, PESSOAS, BUSCAS, USUARIO, REGISTRO_USUARIO,
    PACIENTES, MEDICOS, ENFERMEIROS, FARMACEUTICOS, USUARIOS
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
                                    selected = currentScreen == Screen.BUSCAS,
                                    onClick = { navController.navigate(Screen.BUSCAS) },
                                    icon = { Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Buscas") }
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
                    composable(Screen.HOME.name) { HomeScreen() }
                    composable(Screen.REGISTRO_USUARIO.name) { RegisterUserScreen(RegisterUserController()) }
                    composable(Screen.PESSOAS.name) { PessoasScreen(navController) }
                    composable(Screen.PACIENTES.name) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.PACIENTE, onBack = { navController.navigateBack() }) }
                    composable(Screen.MEDICOS.name) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.MEDICO, onBack = { navController.navigateBack() }) }
                    composable(Screen.ENFERMEIROS.name) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.ENFERMEIRO, onBack = { navController.navigateBack() }) }
                    composable(Screen.FARMACEUTICOS.name) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.FARMACEUTICO, onBack = { navController.navigateBack() }) }
                    composable(Screen.USUARIOS.name) { PessoasCategoryScreen(PessoasCategoryController(), PessoaType.USUARIO, onBack = { navController.navigateBack() }) }
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
fun HomeScreen() {
    Text("Bem-vindo à Home!")
    ConnectDatabaseButton()
}


@Composable
fun ConsultasScreen() {
    Text("Consulte informações.")
}

@Composable
fun UsuarioScreen() {
    Text("Perfil do Usuário.")
}
