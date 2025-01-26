package com.example.fiplan_frontend.auth.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun AuthHeader(headline: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = headline,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun  AuthHeaderPreview() {
    FiplanfrontendTheme {
        Box (
            modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(12.dp)
                        .fillMaxSize(),
        ) {
            AuthHeader(
                headline = "Selamat Datang"
            )
        }
    }

}