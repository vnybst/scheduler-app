package com.vny_bst.schedulerapp.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.util.*

/**
 * Created by Vinay Singh Bisht on 20-Jan-22.
 */
fun datePicker(
    context: Context,
    content: (Date) -> Unit
) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context, { _: DatePicker, yr: Int, mh: Int, dt: Int ->
            c.set(Calendar.YEAR, yr)
            c.set(Calendar.MONTH, mh)
            c.set(Calendar.DAY_OF_MONTH, dt)
            val date = Date(c.timeInMillis)
            content(date)
        }, year, month, day
    ).show()
}

fun timePicker(
    context: Context,
    date: Date,
    content: (Date) -> Unit
) {
    val c = Calendar.getInstance()
    c.time = date
    val mHour = c[Calendar.HOUR_OF_DAY]
    val mMinute = c[Calendar.MINUTE]

    TimePickerDialog(
        context, { _, hour, minute ->
            c.set(Calendar.HOUR_OF_DAY, hour)
            c.set(Calendar.MINUTE, minute)
            content(Date(c.timeInMillis))
        }, mHour, mMinute, false
    ).show()

}