package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.Screen
import com.luisgmr.ifsc.hospital.components.ConnectDatabaseButton
import com.luisgmr.ifsc.hospital.components.HospitalContent
import com.luisgmr.ifsc.hospital.controller.PessoasCategoryController
import com.luisgmr.ifsc.hospital.enums.PessoaType
import com.luisgmr.ifsc.hospital.model.ClasseDados
import com.luisgmr.ifsc.hospital.navigation.NavController
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bed
import compose.icons.fontawesomeicons.solid.Phone
import compose.icons.fontawesomeicons.solid.Syringe
import compose.icons.fontawesomeicons.solid.UserInjured

@Composable
fun HomeScreen(navController: NavController) {
    val pessoasCategoryController = PessoasCategoryController()
    pessoasCategoryController.loadPessoas(PessoaType.PACIENTE)
    HospitalContent(
        backgroundColor = MaterialTheme.colors.background,
        content = {
            Text("Painel de controle", style = MaterialTheme.typography.h3)
            Row {
                Text("Segunda-feira, ", color = Color.Gray, style = MaterialTheme.typography.subtitle2)
                Text("9 de dezembro de 2024", color = MaterialTheme.colors.primary, style = MaterialTheme.typography.subtitle2)
            }
            Spacer(Modifier.size(16.dp))
            Column(
                modifier = Modifier
                    .background(
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.medium)
                    .padding(horizontal = 32.dp, vertical = 32.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Text("Bem-vindo, ", color = Color.White, style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Normal))
                    Text("Dr Wendel Bezerra!", color = Color.White, style = MaterialTheme.typography.h3)
                }
                Text("Tenha um ótimo dia de trabalho!", color = Color.White, style = MaterialTheme.typography.subtitle1)
            }
            Spacer(Modifier.size(16.dp))
            Text("Relevante", style = MaterialTheme.typography.h3)
            Spacer(Modifier.size(8.dp))
            Row(
                horizontalArrangement = spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                        .clickable(
                            onClick = {
                                navController.navigate(Screen.PACIENTES)
                            },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(32.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary, shape = MaterialTheme.shapes.medium)
                        ) {
                            Icon(FontAwesomeIcons.Solid.UserInjured, null, Modifier
                                .size(48.dp)
                                .padding(8.dp),
                                tint = Color.White)
                        }
                        Spacer(Modifier.size(8.dp))
                        Text("Pacientes", style = MaterialTheme.typography.subtitle2, color = Color.Gray)
                        Spacer(Modifier.size(4.dp))
                        Text(ClasseDados.getInstance().pacientes.size.toString(), style = MaterialTheme.typography.h3)
                    }
                }
                Row(
                    modifier = Modifier
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(32.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xff9d1212), shape = MaterialTheme.shapes.medium)
                        ) {
                            Icon(FontAwesomeIcons.Solid.Syringe, null, Modifier
                                .size(48.dp)
                                .padding(8.dp),
                                tint = Color.White)
                        }
                        Spacer(Modifier.size(8.dp))
                        Text("Injeções", style = MaterialTheme.typography.subtitle2, color = Color.Gray)
                        Spacer(Modifier.size(4.dp))
                        Text("0", style = MaterialTheme.typography.h3)
                    }
                }
                Row(
                    modifier = Modifier
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(32.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xff11836e), shape = MaterialTheme.shapes.medium)
                        ) {
                            Icon(FontAwesomeIcons.Solid.Phone, null, Modifier
                                .size(48.dp)
                                .padding(8.dp),
                                tint = Color.White)
                        }
                        Spacer(Modifier.size(8.dp))
                        Text("Consultas", style = MaterialTheme.typography.subtitle2, color = Color.Gray)
                        Spacer(Modifier.size(4.dp))
                        Text("0", style = MaterialTheme.typography.h3)
                    }
                }
                Row(
                    modifier = Modifier
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(32.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xff64107c), shape = MaterialTheme.shapes.medium)
                        ) {
                            Icon(FontAwesomeIcons.Solid.Bed, null, Modifier
                                .size(48.dp)
                                .padding(8.dp),
                                tint = Color.White)
                        }
                        Spacer(Modifier.size(8.dp))
                        Text("Cirurgias", style = MaterialTheme.typography.subtitle2, color = Color.Gray)
                        Spacer(Modifier.size(4.dp))
                        Text("0", style = MaterialTheme.typography.h3)
                    }
                }
            }
        }
    )
}