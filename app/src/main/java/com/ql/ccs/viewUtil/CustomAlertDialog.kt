package com.ql.ccs.viewUtil

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * 自定义对话框封装类
  */
class CustomAlertDialog(
    private val context: Context,
    private val title : String,
    private val message : String,
    /**  确认回调 */
    private val onConfirm: () -> Unit = {},
    /**  可选取消回调 */
    private val onCancel: () -> Unit = {}
) {
    private val dialog: AlertDialog by lazy {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("确定") { _, _ ->
                onConfirm()
                dismiss()
            }
            .setNegativeButton("取消") { _, _ ->
                onCancel()
                dismiss()
            }
            .create()
    }

    fun show() {
        dialog.show()
    }

    private fun dismiss() {
        dialog.dismiss()
    }
}