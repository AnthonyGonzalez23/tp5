package com.example.movies.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import androidx.compose.ui.Alignment

@Composable
fun SearchHeader(
    modifier: Modifier = Modifier,
    onSearchClick: (text: String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
            onClick = { onSearchClick(text) },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHeaderPreview() {
    SearchHeader()
}