package com.example.dialogexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dialogexample.ui.theme.DialogExampleTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClick = {
                            viewModel.triggerLogoutDialog()
                        }
                    )
                    viewModel.dialogState.collectAsStateWithLifecycle().value?.let { dialog ->
                        CustomDialog(
                            dialog = dialog
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = onButtonClick) {
            Text("Global dialog")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            showDialog = true
        }) {
            Text("Screen Level dialog")
        }
    }
    if (showDialog)
        Dialog(
            onDismissRequest = {
                showDialog = false
            },
            content = {
                Column(
                    modifier = Modifier
                        .height(160.dp)
                        .width(360.dp)
                        .background(Color.White, MaterialTheme.shapes.large),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Screen Level dialog")
                    Button(
                        onClick = {
                            showDialog = false
                            // Handle button click
                        }
                    ) {
                        Text("Dismiss")
                    }
                }
            },
        )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DialogExampleTheme {
        Screen() {

        }
    }
}