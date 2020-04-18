package org.d3ifcool.uwangku.database

import androidx.room.TypeConverter
import java.util.*

class DateConverters{
    @TypeConverter
    fun fromTimeStamp(value:Long?) : Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}