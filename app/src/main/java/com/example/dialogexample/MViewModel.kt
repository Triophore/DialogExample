package com.example.dialogexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MViewModel(application: Application): AndroidViewModel(application) {
    private val _dialogState = MutableStateFlow<Dialog?>(null)
    val dialogState = _dialogState.asStateFlow()

    private fun showDialog(event: Dialog) {
        _dialogState.value = event
    }

    private fun dismissDialog() {
        _dialogState.value = null
    }

    // Example usage
    fun triggerLogoutDialog() {
        showDialog(Dialog.LogOut(
            context = getApplication<Application>().baseContext,
            title = "Logout",
            message = "Are you sure?",
            onPositiveClick = {
                logout()
                dismissDialog()
            }
        ))
    }

    fun triggerContactCareDialog() {
        showDialog(Dialog.ContactCustomerCare(
            context = getApplication<Application>().baseContext,
            onPositiveClick = {
                // Handle contact customer care logic
                dismissDialog()
            }
        ))
    }

    private fun logout() {
        // Implement your logout logic here
    }
}