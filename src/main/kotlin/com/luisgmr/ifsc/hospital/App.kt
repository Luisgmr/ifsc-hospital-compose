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
import com.luisgmr.ifsc.hospital.controller.RegisterUserController
import com.luisgmr.ifsc.hospital.screens.RegisterUserScreen
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import com.luisgmr.ifsc.hospital.view.PessoasScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import navcontroller.NavController
import navcontroller.NavigationHost
import navcontroller.composable
import navcontroller.rememberNavController
import java.awt.Dimension


enum class Screen {
    HOME, PESSOAS, BUSCAS, USUARIO, REGISTRO_USUARIO,
    PACIENTES, MEDICOS, ENFERMEIROS, FARMACEUTICOS, USUARIOS
}

@Composable
fun App() {
    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.HOME.name)
    val currentScreen by remember {
        navController.currentScreen
    }

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
                                    selected = false,
                                    onClick = { navController.navigate(Screen.HOME.name) },
                                    icon = { Icon(FontAwesomeIcons.Solid.AddressCard, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Home") }
                                )
                                HospitalNavigationRailItem(
                                    selected = false,
                                    onClick = { navController.navigate(Screen.PESSOAS.name) },
                                    icon = { Icon(FontAwesomeIcons.Solid.HospitalUser, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Pessoas") }
                                )
                                HospitalNavigationRailItem(
                                    selected = false,
                                    onClick = { navController.navigate(Screen.MEDICOS.name) },
                                    icon = { Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                    label = { Text("Buscas") }
                                )
                            }
                            HospitalNavigationRailItem(
                                selected = false,
                                onClick = { navController.navigate(Screen.REGISTRO_USUARIO.name) },
                                icon = { Icon(FontAwesomeIcons.Solid.AngleRight, contentDescription = null, modifier = Modifier.size(32.dp)) },
                                label = { Text("Registrar") }
                            )
                        }
                    }
                }

                NavigationHost(navController = navController) {
                    composable(Screen.HOME.name) { HomeScreen() }
                    composable(Screen.REGISTRO_USUARIO.name) { RegisterUserScreen(RegisterUserController()) }
                    composable(Screen.PESSOAS.name) {
                        PessoasScreen(navController
//                            onPacientesClick = { navController.navigate(Screen.PACIENTES.name) },
//                            onMedicosClick = { navController.navigate(Screen.MEDICOS.name) },
//                            onEnfermeirosClick = { navController.navigate(Screen.ENFERMEIROS.name) },
//                            onFarmaceuticosClick = { navController.navigate(Screen.FARMACEUTICOS.name) },
//                            onUsuariosClick = { navController.navigate(Screen.USUARIOS.name) }
                        )
                    }
                    composable(Screen.PACIENTES.name) { PessoasCategoryScreen("Pacientes", onBack = { navController.navigateBack() }) }
                    composable(Screen.MEDICOS.name) { PessoasCategoryScreen("Médicos", onBack = { navController.navigateBack() }) }
                    composable(Screen.ENFERMEIROS.name) { PessoasCategoryScreen("Enfermeiros", onBack = { navController.navigateBack() }) }
                    composable(Screen.FARMACEUTICOS.name) { PessoasCategoryScreen("Farmacêuticos", onBack = { navController.navigateBack() }) }
                    composable(Screen.USUARIOS.name) { PessoasCategoryScreen("Usuários", onBack = { navController.navigateBack() }) }
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
fun PessoasCategoryScreen(category: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
            }
            Text(text = "Lista de $category", style = MaterialTheme.typography.h4)
        }
        Text("Aqui estará a lista de $category.")
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
