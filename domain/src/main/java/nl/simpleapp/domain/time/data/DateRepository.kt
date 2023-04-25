package nl.simpleapp.domain.time.data
import java.time.LocalDate

interface DateRepository {
    suspend fun fetchDate() : LocalDate
}