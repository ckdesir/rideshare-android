package com.cornellappdev.scoop.ui.components.calendar

import javax.inject.Inject
import javax.inject.Singleton

typealias CalendarYear = List<CalendarMonth>

@Singleton
class DatesLocalDataSource @Inject constructor() {
    private val january2022 = CalendarMonth(
        name = "January",
        year = "2022",
        numDays = 31,
        monthNumber = 1,
        startDayOfWeek = DayOfWeek.Friday
    )
    private val february2022 = CalendarMonth(
        name = "February",
        year = "2022",
        numDays = 28,
        monthNumber = 2,
        startDayOfWeek = DayOfWeek.Monday
    )
    private val march2022 = CalendarMonth(
        name = "March",
        year = "2022",
        numDays = 31,
        monthNumber = 3,
        startDayOfWeek = DayOfWeek.Monday
    )
    private val april2022 = CalendarMonth(
        name = "April",
        year = "2022",
        numDays = 30,
        monthNumber = 4,
        startDayOfWeek = DayOfWeek.Thursday
    )
    private val may2022 = CalendarMonth(
        name = "May",
        year = "2022",
        numDays = 31,
        monthNumber = 5,
        startDayOfWeek = DayOfWeek.Tuesday
    )
    private val june2022 = CalendarMonth(
        name = "June",
        year = "2022",
        numDays = 30,
        monthNumber = 6,
        startDayOfWeek = DayOfWeek.Tuesday
    )
    private val july2022 = CalendarMonth(
        name = "July",
        year = "2022",
        numDays = 31,
        monthNumber = 7,
        startDayOfWeek = DayOfWeek.Thursday
    )
    private val august2022 = CalendarMonth(
        name = "August",
        year = "2022",
        numDays = 31,
        monthNumber = 8,
        startDayOfWeek = DayOfWeek.Sunday
    )
    private val september2022 = CalendarMonth(
        name = "September",
        year = "2022",
        numDays = 30,
        monthNumber = 9,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val october2022 = CalendarMonth(
        name = "October",
        year = "2022",
        numDays = 31,
        monthNumber = 10,
        startDayOfWeek = DayOfWeek.Friday
    )
    private val november2022 = CalendarMonth(
        name = "November",
        year = "2022",
        numDays = 30,
        monthNumber = 11,
        startDayOfWeek = DayOfWeek.Monday
    )
    private val december2022 = CalendarMonth(
        name = "December",
        year = "2022",
        numDays = 31,
        monthNumber = 12,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    val year2022: CalendarYear = listOf(
        january2022,
        february2022,
        march2022,
        april2022,
        may2022,
        june2022,
        july2022,
        august2022,
        september2022,
        october2022,
        november2022,
        december2022
    )
}













