package simpleapp.presentation.weather

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object DateUIMapper {

    @SuppressLint("NewApi")
    fun mapToUIModel(date: String): DateUIModel {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, EEEE")
        val currentDate = LocalDate.parse(date, dateTimeFormatter)
        var day = currentDate.dayOfMonth.toString()

        if (Locale.getDefault() == Locale.KOREA) {
           day += "일"
        }

        val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val year = currentDate.year.toString()
        val weekday = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

        return DateUIModel(
            day = day,
            month = month,
            year = year,
            dayOfWeek = weekday
        )
    }

    fun mapDateUIModelToString(date: DateUIModel): String {
        return String.format("%s %s, %s", date.day, date.month, date.dayOfWeek)
    }
}