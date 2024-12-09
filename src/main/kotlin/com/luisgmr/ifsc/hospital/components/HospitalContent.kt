package com.luisgmr.ifsc.hospital.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisgmr.ifsc.hospital.themes.HospitalTheme

@Composable
fun HospitalContent(
    content: @Composable ColumnScope.() -> Unit = {},
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
) {
    HospitalTheme {
        Box(
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.large)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .background(Color.White, MaterialTheme.shapes.medium)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = verticalArrangement,
            ) {
                content()
            }
        }
    }

}