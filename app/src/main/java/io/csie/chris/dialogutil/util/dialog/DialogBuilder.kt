package io.csie.chris.dialogutil.util.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AlertDialog

class DialogBuilder(
    private val context: Context,
    private val themeResId: Int?
) {

    private val builder =
        if (themeResId != null)
            AlertDialog.Builder(context, themeResId)
        else
            AlertDialog.Builder(context)

    // Title
    var title: CharSequence? = null
        set(value) {
            builder.setTitle(value)
        }

    var titleResId: Int? = null
        set(value) {
            value?.let { builder.setTitle(value) }
        }

    // Message
    var message: CharSequence? = null
        set(value) {
            builder.setMessage(value)
        }

    // Icon
    var icon: Drawable? = null
        set(value) {
            builder.setIcon(value)
        }

    var iconResId: Int? = null
        set(value) {
            value?.let { builder.setIcon(value) }
        }

    // Positive button
    fun positiveButton(buttonText: String, onClicked: ((dialog: DialogInterface) -> Unit)?) {
        builder.setPositiveButton(buttonText) { dialog, _ ->
            onClicked?.run {
                onClicked(dialog)
            }
        }
    }

    fun positiveButton(buttonTextResId: Int, onClicked: ((dialog: DialogInterface) -> Unit)?) {
        builder.setPositiveButton(buttonTextResId) { dialog, _ ->
            onClicked?.run {
                this(dialog)
            }
        }
    }

    // Negative button
    fun negativeButton(buttonText: String, onClicked: ((dialog: DialogInterface) -> Unit)?) {
        builder.setNegativeButton(buttonText) { dialog, _ ->
            onClicked?.run {
                this(dialog)
            }
        }
    }

    fun negativeButton(buttonTextResId: Int, onClicked: ((dialog: DialogInterface) -> Unit)?) {
        builder.setNegativeButton(buttonTextResId) { dialog, _ ->
            onClicked?.run {
                this(dialog)
            }
        }
    }

    fun build() = builder.create()

    fun okButton(action: ((dialog: DialogInterface) -> Unit)?) {
        positiveButton(android.R.string.ok, action)
    }

    fun cancelButton(action: ((dialog: DialogInterface) -> Unit)?) {
        negativeButton(android.R.string.cancel, action)
    }
}