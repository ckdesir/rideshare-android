package com.cornellappdev.scoop.ui.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.O)
@Singleton
class DatesRepository @Inject constructor(
    datesLocalDataSource: DatesLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    val calendarYear = datesLocalDataSource.currentYear
    val datesSelected = DatesSelectedState(datesLocalDataSource.currentYear)

    suspend fun onDaySelected(daySelected: DaySelected) = withContext(defaultDispatcher) {
        datesSelected.daySelected(daySelected)
    }
}