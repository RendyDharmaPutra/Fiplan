package com.example.fiplan_frontend.auth.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun AuthAction(
    question: String,
    guidance: String,
    action: () -> Unit = { }
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =  Arrangement.Center
    ) {
        Text(
            text = "$question ",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = guidance,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { action() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun  AuthActionPreview() {
    FiplanfrontendTheme {
        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
                .fillMaxSize(),
        ) {
            AuthAction(question = "Belum punya akun?", guidance = "Daftar disini")
        }
    }

}