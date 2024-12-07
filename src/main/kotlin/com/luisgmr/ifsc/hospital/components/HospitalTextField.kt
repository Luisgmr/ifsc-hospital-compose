package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun HospitalTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.background,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector? = null,
    maxLines: Int = 1,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, shape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Spacer(Modifier.size(16.dp))
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Gray)
        } else {
            Spacer(Modifier.size(8.dp))
        }
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = textStyle.copy(color = MaterialTheme.colors.onSurface),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            decorationBox = { innerTextField ->
                Box(
                    Modifier.padding(vertical = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            },
            maxLines = maxLines,
        )
    }
}
