package com.cornellappdev.scoop.ui.components.calendar

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatesRepository @Inject constructor(
    datesLocalDataSource: DatesLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    val calendarYear = datesLocalDataSource.year2022
    val datesSelected = DatesSelectedState(datesLocalDataSource.year2022)

    suspend fun onDaySelected(daySelected: DaySelected) = withContext(defaultDispatcher) {
        datesSelected.daySelected(daySelected)
    }
}