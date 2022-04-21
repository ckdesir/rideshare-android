package com.cornellappdev.scoop.ui.components.post

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.*


fun createDatePickerDialog(
    context: Context,
    setDateText: (String) -> Unit,
    dateFormatter: SimpleDateFormat
): DatePickerDialog {
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

    val datePickerDialog = DatePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            setDateText(dateFormatter.format(cal.time))
        }, date.year, date.monthNumber, date.dayOfMonth
    )
    datePickerDialog.datePicker.minDate = currentMoment.toEpochMilliseconds()
    return datePickerDialog
}

fun createTimePickerDialog(
    context: Context,
    setTimeText: (String) -> Unit,
    timeFormatter: SimpleDateFormat
): TimePickerDialog {
    val currentMoment = Clock.System.now()
    val date: LocalDateTime =
        currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

    val timePickerDialog = TimePickerDialog(
        context,
        android.R.style.Theme_DeviceDefault_Dialog_Alert,
        { _: TimePicker, hourOfDay: Int, minute: Int ->
            val cal = Calendar.getInstance()
            cal[Calendar.HOUR_OF_DAY] = hourOfDay
            cal[Calendar.MINUTE] = minute
            setTimeText(timeFormatter.format(cal.time))
        }, date.hour, date.minute, false
    )
    return timePickerDialog
}