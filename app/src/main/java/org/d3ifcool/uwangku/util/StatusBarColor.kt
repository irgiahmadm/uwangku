package org.d3ifcool.uwangku.util

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity


object StatusBarColor {
    fun updateStatusBarColor(color: String,activity:FragmentActivity) { // Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor(color))
        }
    }
}