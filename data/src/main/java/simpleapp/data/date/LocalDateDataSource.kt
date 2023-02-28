package simpleapp.data.date

import android.annotation.SuppressLint
import java.time.LocalDate

class LocalDateDataSource : DateDataSource {

    @SuppressLint("NewApi")
    override suspend fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }
}