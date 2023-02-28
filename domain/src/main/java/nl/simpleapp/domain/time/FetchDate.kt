package nl.simpleapp.domain.time

import nl.simpleapp.domain.time.data.DateRepository
import nl.simpleapp.domain.time.data.FormatDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.TextStyle
import java.util.*

class FetchDate(private val dateRepository: DateRepository) : FormatDate {

    @Suppress("NewApi")
    private val dateFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendPattern("dd/MM/yyyy")
        .toFormatter(Locale.getDefault())

    @Suppress("NewApi")
    override suspend operator fun invoke(): String {
        val currentDate = dateRepository.fetchDate()
        val weekday = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        return "${currentDate.format(dateFormatter)}, $weekday"
    }
}