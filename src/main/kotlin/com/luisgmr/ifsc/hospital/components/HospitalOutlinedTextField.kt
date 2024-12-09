package com.luisgmr.ifsc.hospital.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

enum class InputType {
    DATE, // Formato: dd/MM/yyyy
    TEXT,
    NUMBER,
    CPF,
    CNPJ,
    CEP,
    RG,
    PASSWORD
}

@Composable
fun HospitalOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    fieldType: InputType = InputType.TEXT,
    placeholder: String? = null,
    maxLines: Int = 1,
    maxLength: Int? = null,
) {
    OutlinedTextField(
        value = value,
        maxLines = maxLines,
        onValueChange = { newText ->
            val filtered = when (fieldType) {
                InputType.CPF, InputType.CNPJ, InputType.CEP, InputType.RG, InputType.NUMBER, InputType.DATE -> {
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
        label = { Text(label) },
        placeholder = { if (placeholder != null) Text(placeholder) },
        shape = MaterialTheme.shapes.medium,
        visualTransformation = when (fieldType) {
            InputType.DATE -> DateTransformation()
            InputType.TEXT -> VisualTransformation.None
            InputType.NUMBER -> VisualTransformation.None
            InputType.CPF -> MaskVisualTransformation("###.###.###-##", 11)
            InputType.CNPJ -> MaskVisualTransformation("##.###.###/####-##", 14)
            InputType.CEP -> MaskVisualTransformation("#####-###", 8)
            InputType.RG -> MaskVisualTransformation("##.###.###-#", 9)
            InputType.PASSWORD -> PasswordVisualTransformation()
        },
        modifier = modifier
    )
}