package com.cornellappdev.scoop.ui.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Month
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

typealias CalendarYear = List<CalendarMonth>

@RequiresApi(Build.VERSION_CODES.O)
@Singleton
class DatesLocalDataSource @Inject constructor() {

    private fun getMonthLength(month: Month, year: Int): Int {

        val leap = if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                year % 400 == 0
            } else
                true
        } else
            false

        return month.length(leap)
    }

    private fun getStartDayOfWeek(month: Int, year: Int): DayOfWeek {

        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month - 1
        calendar[Calendar.DAY_OF_MONTH] = 1

        return when (calendar[Calendar.DAY_OF_WEEK]) {
            0 -> DayOfWeek.Sunday
            1 -> DayOfWeek.Monday
            2 -> DayOfWeek.Tuesday
            3 -> DayOfWeek.Wednesday
            4 -> DayOfWeek.Thursday
            5 -> DayOfWeek.Friday
            else -> DayOfWeek.Saturday
        }
    }

    var currentYear: CalendarYear

    private val tempMonthList: MutableList<CalendarMonth> = mutableListOf()

    init {

        val currentDate = Date()

        var currMonth = currentDate.month

        var currYear = currentDate.year + 1900

        for (i in 0..5) {

            if (currMonth + 1 > 12) {

                currYear += 1

                currMonth = 1
            } else {
                currMonth += 1
            }

            tempMonthList.add(

                CalendarMonth(
                    name = Month(currMonth).toString(),
                    year = currYear.toString(),
                    numDays = getMonthLength(Month(currMonth), currYear),
                    monthNumber = currMonth,
                    startDayOfWeek = getStartDayOfWeek(currMonth, currYear)
                )
            )
        }

        currentYear = tempMonthList
    }
}













