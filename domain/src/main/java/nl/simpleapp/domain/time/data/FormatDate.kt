package nl.simpleapp.domain.time.data

interface FormatDate {
    suspend operator fun invoke() : String
}