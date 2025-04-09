package com.example.dialogexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dialogexample.ui.theme.DialogExampleTheme
import com.example.dialogexample.ui.composables.CustomDialog
import com.example.dialogexample.ui.screens.Screen

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