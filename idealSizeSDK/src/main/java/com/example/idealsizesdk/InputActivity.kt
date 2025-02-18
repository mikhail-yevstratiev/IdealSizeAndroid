package com.example.idealsizesdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.idealsizesdk.ui.theme.IdealSizeAndroidSDKTheme

class InputActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdealSizeAndroidSDKTheme {
                val height = remember { mutableStateOf("") }
                val weight = remember { mutableStateOf("") }
                val errorMessage = remember { mutableStateOf<String?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Find Your Perfect Fit ") },
                        )
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Height:")
                            Spacer(modifier = Modifier.width(16.dp))
                            OutlinedTextField(
                                value = height.value,
                                onValueChange = { height.value = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("cm", Modifier.width(32.dp))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("Weight:")
                            Spacer(modifier = Modifier.width(16.dp))
                            OutlinedTextField(
                                value = weight.value,
                                onValueChange = { weight.value = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("kg", Modifier.width(32.dp))
                        }
                        Button(
                            onClick = {
                                val result = calculateSize(height.value, weight.value);
                                errorMessage.value = result;


                            },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Get Size Recommendation")
                        }

                        errorMessage.value?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(it, color = Color.Red)
                        }
                    }
                }
            }
        }
    }

    private fun calculateSize(height: String, weight: String): String? {
        val heightValue = height.toFloatOrNull()
        val weightValue = weight.toFloatOrNull()

        if (heightValue == null || weightValue == null || heightValue <= 0 || weightValue <= 0) {
            return "Height and weight should be positive numbers"
        }

        // Calculate BMI: BMI = weight (kg) / (height (m) * height (m))
        val heightInMeters = heightValue / 100
        val bmi = weightValue / (heightInMeters * heightInMeters)

        try {
            val recommendedSize = IdealSize.sizeByBMI(bmi)
            val intent = Intent(this@InputActivity, ResultActivity::class.java).apply {
                putExtra("SIZE", recommendedSize.size)
            }
            startActivity(intent)
        } catch (e: InvalidBMIError) {
             return "Invalid height or weight"
        }

        return null;
    }
}