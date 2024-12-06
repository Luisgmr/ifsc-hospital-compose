package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.Screen
import com.luisgmr.ifsc.hospital.components.PessoasButton
import com.luisgmr.ifsc.hospital.themes.HospitalTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*
import com.luisgmr.ifsc.hospital.navigation.NavController

@Composable
fun PessoasScreen(navController: NavController) {
    HospitalTheme {
        Box(
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.large)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color.White, MaterialTheme.shapes.medium),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Clique para acessar o menu de pessoas",
                    style = MaterialTheme.typography.h3
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PessoasButton(
                        label = "Pacientes",
                        icon = FontAwesomeIcons.Solid.UserInjured,
                        onClick = { navController.navigate(Screen.PACIENTES.name) }
                    )
                    PessoasButton(
                        label = "Médicos",
                        icon = FontAwesomeIcons.Solid.UserMd,
                        onClick = { navController.navigate(Screen.MEDICOS.name) }
                    )
                    PessoasButton(
                        label = "Enfermeiros",
                        icon = FontAwesomeIcons.Solid.UserNurse,
                        onClick = { navController.navigate(Screen.ENFERMEIROS.name) }
                    )
                    PessoasButton(
                        label = "Farmaceuticos",
                        icon = FontAwesomeIcons.Solid.UserTie,
                        onClick = { navController.navigate(Screen.FARMACEUTICOS.name) }
                    )
                    PessoasButton(
                        label = "Usuários",
                        icon = FontAwesomeIcons.Solid.UserAlt,
                        onClick = { navController.navigate(Screen.USUARIOS.name) }
                    )

                }
            }
        }
    }
}