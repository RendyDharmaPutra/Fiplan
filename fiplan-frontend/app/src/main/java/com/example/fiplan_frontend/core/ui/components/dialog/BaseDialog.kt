package com.example.fiplan_frontend.core.ui.components.dialog

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun BaseDialog(
    title: String,
    content: String,
    closeDialog: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f))
            .clickable { closeDialog() }
            .zIndex(3f)
    ) {

        AlertDialog(
            modifier = Modifier.zIndex(2f),
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = {
                closeDialog()
            },
            title = { Text(text = title, color = MaterialTheme.colorScheme.onBackground) },
            text = { Text(text = content, color = MaterialTheme.colorScheme.onSurface) },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    Text(text = "Tutup",)
                }
            }
        )
    }

}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun  RegisterScreenPreview() {
    FiplanfrontendTheme {
        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            BaseDialog(
                title = "Gagal Mendaftar Pengguna",
                content = "Username telah terdaftar",
            ) {  }
        }
    }
}