package com.example.dialogexample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.ui.res.painterResource


@Composable
fun CustomDialog(
    dialog: Dialog?, // Make the dialog argument nullable
) {
    dialog?.let { // Only show the dialog if the dialog object is not null
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.large
                    )
                    .clip(MaterialTheme.shapes.large)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {} // Prevent clicks from propagating outside the dialog content
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Title Section
                    it.title?.let { title ->
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Content Section
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.width(IntrinsicSize.Min)
                    ) {
                        it.logo?.let { id ->
                            Image(
                                painter = painterResource(id),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                            )
                        }

                        it.message?.let { message ->
                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    // Buttons Section
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        it.cancelButtonText?.let { cancelText ->
                            OutlinedButton(
                                onClick = { it.onNegativeClick?.invoke() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Text(text = cancelText)
                            }
                        }

                        it.okButtonText?.let { okText ->
                            Button(
                                onClick = { it.onPositiveClick?.invoke() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Text(text = okText)
                            }
                        }
                    }
                }
            }
        }
    }
}