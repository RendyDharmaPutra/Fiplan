package com.example.fiplan_frontend.core.ui.components.layout.page

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun PageScaffold(
    appbar: @Composable () -> Unit = {},
    body: @Composable (modifier: Modifier) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { appbar() },
        content = { innerPadding ->
            body(Modifier.padding(innerPadding))
        }
    )
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun  PageScaffoldPreview() {
    FiplanfrontendTheme {
            PageScaffold(
                appbar = {},
                body = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                    ) {
                        Text("Page Screen")
                    }
                }
            )
    }
}