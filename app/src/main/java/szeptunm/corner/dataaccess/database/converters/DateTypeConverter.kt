package szeptunm.corner.dataaccess.database.converters

import androidx.room.TypeConverter
import org.joda.time.LocalDateTime

class DateTypeConverter {

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String? = localDateTime.toString()

    @TypeConverter
    fun toLocalDateTime(date: String): LocalDateTime = LocalDateTime.parse(date)
}