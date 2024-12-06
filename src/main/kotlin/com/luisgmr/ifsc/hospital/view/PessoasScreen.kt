package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.components.PessoasButton
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.*

@Composable
fun PessoasScreen() {
    Box(
        modifier = Modifier
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, MaterialTheme.shapes.medium)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PessoasButton(
                    label = "Pacientes",
                    icon = FontAwesomeIcons.Solid.UserInjured,
                    onClick = {}
                )
                PessoasButton(
                    label = "Médicos",
                    icon = FontAwesomeIcons.Solid.UserMd,
                    onClick = {}
                )
                PessoasButton(
                    label = "Enfermeiros",
                    icon = FontAwesomeIcons.Solid.UserNurse,
                    onClick = {}
                )
                PessoasButton(
                    label = "Farmaceuticos",
                    icon = FontAwesomeIcons.Solid.UserTie,
                    onClick = {}
                )
                PessoasButton(
                    label = "Usuários",
                    icon = FontAwesomeIcons.Solid.UserAlt,
                    onClick = {}
                )

            }
        }
    }
}