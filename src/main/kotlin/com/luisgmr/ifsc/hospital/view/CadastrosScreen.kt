package com.luisgmr.ifsc.hospital.view

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.DataTableState
import com.seanproctor.datatable.material3.DataTable
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Anchor

@Composable
fun CadastrosScreen() {
    var selectedRow by remember { mutableStateOf<Int?>(null) }
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        DataTable(
            columns = listOf(
                DataColumn {
                    Text("Header A")
                },
                DataColumn {
                    Text("Header B")
                },
                DataColumn(Alignment.CenterEnd) {
                    Text("Header C")
                },
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            row {
                onClick = { selectedRow = 0 }
                cell { Text("Cell A1") }
                cell { Text("Cell B1") }
                cell { Text("Cell C1") }
            }
            row {
                onClick = { selectedRow = 1 }
                cell { Text("Cell A2") }
                cell { Text("Cell B2") }
                cell { Text("Cell C2") }
            }
        }
        PaginatedDataTable(
            columns = listOf(
                DataColumn {
                    Text("Column1")
                },
                DataColumn {
                    Text("Column2")
                },
                DataColumn {
                    Text("Column3")
                },
            ),
            state = rememberPaginatedDataTableState(5),
        ) {
            for (rowIndex in 0 until 100) {
                row {
                    onClick = { println("Row clicked: $rowIndex") }
                    cell {
                        Text("Row $rowIndex, column 1")
                    }
                    cell {
                        Text("Row $rowIndex, column 2")
                    }
                    cell {
                        Text("Row $rowIndex, column 3")
                    }
                }
            }
        }
    }
}