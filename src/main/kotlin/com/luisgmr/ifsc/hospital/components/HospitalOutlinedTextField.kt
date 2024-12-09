package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

enum class InputType {
    DATE, // Formato: dd/MM/yyyy
    TEXT,
    NUMBER,
    CPF,
    CNPJ,
    CEP,
    RG,
    PASSWORD,
    PHONE
}

@Composable
fun HospitalOutlinedTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    fieldType: InputType = InputType.TEXT,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    maxLines: Int = 1,
    maxLength: Int? = null,
    isError: Boolean = false, // Validação de erro
    errorMessage: String = "Valor inválido",
    isEnabled: Boolean = true,
) {
    Column(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = value,
            maxLines = maxLines,
            onValueChange = { newText ->
                val filtered = when (fieldType) {
                    InputType.CPF, InputType.CNPJ, InputType.CEP, InputType.RG, InputType.NUMBER, InputType.DATE, InputType.PHONE -> {
                        newText.filter { it.isDigit() }
                    }
                    else -> newText
                }
                if (maxLength != null && filtered.length > maxLength) {
                    onValueChange(filtered.take(maxLength))
                } else {
                    onValueChange(filtered)
                }
            },
            enabled = isEnabled,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { if (placeholder != null) Text(placeholder) },
            shape = MaterialTheme.shapes.medium,
            isError = isError, // Mostra o estado de erro
            visualTransformation = when (fieldType) {
                InputType.DATE -> DateTransformation()
                InputType.TEXT -> VisualTransformation.None
                InputType.NUMBER -> VisualTransformation.None
                InputType.CPF -> MaskVisualTransformation("###.###.###-##", 11)
                InputType.CNPJ -> MaskVisualTransformation("##.###.###/####-##", 14)
                InputType.CEP -> MaskVisualTransformation("#####-###", 8)
                InputType.RG -> MaskVisualTransformation("##.###.###-#", 9)
                InputType.PASSWORD -> PasswordVisualTransformation()
                InputType.PHONE -> MaskVisualTransformation("(##) #####-####", 11)
            },
        )
        if (isError) {
            Spacer(modifier = Modifier.height(2.dp)) // Espaçamento entre o campo e a mensagem de erro
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.caption, // Estilo menor para a mensagem de erro
                color = MaterialTheme.colors.error,
            )
        }
    }
}