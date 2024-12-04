package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager

@Composable
fun ConnectDatabaseButton() {
    var connectionStatus by remember { mutableStateOf("Pressione para conectar ao banco") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isLoading = true
                connectionStatus = "Conectando..."
                // Conecta ao banco em um thread separado
                connectToDatabase(
                    onSuccess = {
                        connectionStatus = "Conexão bem-sucedida!"
                        isLoading = false
                    },
                    onFailure = { error ->
                        connectionStatus = "Erro: $error"
                        isLoading = false
                    }
                )
            },
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text("Conectar ao Banco")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(connectionStatus)
    }
}

fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
    // Execute a conexão em um Coroutine para evitar travar a UI
    kotlinx.coroutines.GlobalScope.launch {
        try {
            withContext(Dispatchers.IO) {
                val connection = createDatabaseConnection()
                connection.close() // Feche a conexão para evitar vazamentos
            }
            onSuccess()
        } catch (e: Exception) {
            onFailure(e.message ?: "Erro desconhecido")
        }
    }
}

fun createDatabaseConnection(): Connection {
    val url = "jdbc:mysql://192.168.3.67:3306/hospital_compose" // Substitua com sua URL do banco de dados
    val user = "goat" // Substitua pelo seu usuário do banco de dados
    val password = "Refacty_db5498!#" // Substitua pela sua senha do banco de dados

    return DriverManager.getConnection(url, user, password)
}