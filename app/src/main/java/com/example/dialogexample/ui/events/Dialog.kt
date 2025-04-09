package com.example.dialogexample.ui.events

import android.content.Context
import com.example.dialogexample.R

sealed class Dialog(
    open val title: String? = null,
    open val message: String? = null,
    open val logo: Int? = null,
    open val okButtonText: String? = null,
    open val cancelButtonText: String? = null,
    open val onPositiveClick: (() -> Unit)? = null,
    open val onNegativeClick: (() -> Unit)? = null
) {
    data class LogOut(val context: Context, override val title: String?, override val message: String?, override val onPositiveClick: () -> Unit) : Dialog(
        title = title,
        message = message,
        okButtonText = context.getString(R.string.button_text_yes),
        cancelButtonText = context.getString(R.string.button_text_no),
        logo = R.drawable.icon_logout
    )

    data class ContactCustomerCare(val context: Context, override val onPositiveClick: () -> Unit) : Dialog(
        title = context.getString(R.string.title_text_contact_customer_care),
        message = context.getString(R.string.message_text_contact_customer_care),
        okButtonText = context.getString(R.string.button_text_ok),
        logo = R.drawable.icon_alert,
    )
}