package com.example.idealsizesdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.idealsizesdk.ui.theme.IdealSizeAndroidSDKTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val size = intent.getStringExtra("SIZE") ?: "Unknown"
        setContent {
            IdealSizeAndroidSDKTheme {
                BuildContent(size = size, finish = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildContent(size: String, finish: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Your Recommended Size: $size") }) }
    ) { innerPadding ->
        Content(size = size, modifier = Modifier.padding(innerPadding), finish = finish)
    }
}

@Composable
private fun Content(size: String, modifier: Modifier = Modifier, finish: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Based on your info, size $size is recommended.")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { finish() }) {
            Text(text = "OK")
        }
    }
}