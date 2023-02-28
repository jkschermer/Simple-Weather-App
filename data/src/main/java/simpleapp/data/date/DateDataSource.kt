package simpleapp.data.date

import java.time.LocalDate

interface DateDataSource {
    suspend fun getCurrentDate(): LocalDate
}