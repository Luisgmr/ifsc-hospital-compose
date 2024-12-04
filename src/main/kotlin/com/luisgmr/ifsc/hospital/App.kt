package com.luisgmr.ifsc.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import java.awt.Dimension

//
enum class Screen {
    HOME, GERENCIAR, BUSCAS, USUARIO
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

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
                        .padding(8.dp)
                ) {
                    NavigationRail(
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White,
                    ) {
                        HospitalNavigationRailItem(
                            selected = currentScreen == Screen.HOME,
                            onClick = { currentScreen = Screen.HOME },
                            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                            label = { Text("Home") }
                        )
                        HospitalNavigationRailItem(
                            selected = currentScreen == Screen.GERENCIAR,
                            onClick = { currentScreen = Screen.GERENCIAR },
                            icon = { Icon(Icons.Filled.Settings, contentDescription = "Gerenciar") },
                            label = { Text("Gerenciar") }
                        )
                        HospitalNavigationRailItem(
                            selected = currentScreen == Screen.BUSCAS,
                            onClick = { currentScreen = Screen.BUSCAS },
                            icon = { Icon(Icons.Filled.Search, contentDescription = "Buscas") },
                            label = { Text("Buscas") }
                        )
                    }
                }


                when (currentScreen) {
                    Screen.HOME -> RegisterUserScreen(RegisterUserController())
                    Screen.GERENCIAR -> GerenciarScreen()
                    Screen.BUSCAS -> ConsultasScreen()
                    Screen.USUARIO -> UsuarioScreen()
                }
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        resizable = true
    ) {
        this.window.minimumSize = Dimension(800, 600)
        App()
    }
}

@Composable
fun HomeScreen() {
    Text("Bem-vindo à Home!")
    ConnectDatabaseButton()
}

@Composable
fun GerenciarScreen() {
    Text("Gerencie pacientes, medicamentos, etc.")
}

@Composable
fun ConsultasScreen() {
    Text("Consulte informações.")
}

@Composable
fun UsuarioScreen() {
    Text("Perfil do Usuário.")
}
