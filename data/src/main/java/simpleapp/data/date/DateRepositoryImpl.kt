package simpleapp.data.date

import android.annotation.SuppressLint
import nl.simpleapp.domain.time.data.DateRepository
import java.time.LocalDate

class DateRepositoryImpl(private val dateDataSource: DateDataSource) : DateRepository {

    @SuppressLint("NewApi")
    override suspend fun fetchDate(): LocalDate {
        return dateDataSource.getCurrentDate()
    }
}