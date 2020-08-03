package io.csie.chris.dialogutil.util.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import io.csie.chris.dialogutil.R

object DialogUtil {

    class AlertDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            return DialogBuilder(
                context = activity as Context,
                themeResId = themeResId
            ).apply {

                okButton {} // default use ok button
                unit?.run { this() }

            }.build()
        }

        companion object {
            var themeResId: Int? = null
            var unit: (DialogBuilder.() -> Unit)? = null

            // Create Dialog Fragment
            fun newInstance(
                themeResId: Int? = null,
                unit: (DialogBuilder.() -> Unit)? = null
            ): AlertDialogFragment {

                val dialog = AlertDialogFragment()

                this.themeResId = themeResId ?: R.style.AlertDialogCustom
                this.unit = unit

                return dialog
            }
        }
    }
}

fun DialogFragment.noCancel(): DialogFragment = apply { isCancelable = false }