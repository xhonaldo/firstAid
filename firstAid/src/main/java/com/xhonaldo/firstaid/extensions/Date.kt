package com.xhonaldo.firstaid.extensions

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * Converts a Date object to a formatted string.
 * @param format The desired format of the output string.
 * @return The formatted date string.
 */
fun Date.convertTo(format: String): String? {
    val format = SimpleDateFormat(format)
    return format.format(this)
}

/**
 * Converts a Date object to a Calendar object.
 * @return The Calendar object representing the same date.
 */
fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

/**
 * Checks if the date is in the future.
 * @return True if the date is in the future, otherwise false.
 */
fun Date.isFuture(): Boolean {
    return !Date().before(this)
}

/**
 * Checks if the date is in the past.
 * @return True if the date is in the past, otherwise false.
 */
fun Date.isPast(): Boolean {
    return Date().before(this)
}

/**
 * Checks if the date is today.
 * @return True if the date is today, otherwise false.
 */
fun Date.isToday(): Boolean {
    return DateUtils.isToday(this.time)
}

/**
 * Checks if the date is yesterday.
 * @return True if the date is yesterday, otherwise false.
 */
fun Date.isYesterday(): Boolean {
    return DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)
}

/**
 * Checks if the date is tomorrow.
 * @return True if the date is tomorrow, otherwise false.
 */
fun Date.isTomorrow(): Boolean {
    return DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)
}

/**
 * Returns the current date.
 * @return The current date.
 */
fun Date.today(): Date {
    return Date()
}

/**
 * Returns the date of yesterday.
 * @return The date of yesterday.
 */
fun Date.yesterday(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, -1)
    return cal.time
}

/**
 * Returns the date of tomorrow.
 * @return The date of tomorrow.
 */
fun Date.tomorrow(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    return cal.time
}

/**
 * Returns the hour component of the date.
 * @return The hour component.
 */
fun Date.hour(): Int {
    return this.toCalendar().get(Calendar.HOUR)
}

/**
 * Returns the minute component of the date.
 * @return The minute component.
 */
fun Date.minute(): Int {
    return this.toCalendar().get(Calendar.MINUTE)
}

/**
 * Returns the second component of the date.
 * @return The second component.
 */
fun Date.second(): Int {
    return this.toCalendar().get(Calendar.SECOND)
}

/**
 * Returns the month component of the date.
 * @return The month component.
 */
fun Date.month(): Int {
    return this.toCalendar().get(Calendar.MONTH) + 1
}

/**
 * Returns the name of the month.
 * @param locale The locale for the month name. Default is the system locale.
 * @return The name of the month.
 */
fun Date.monthName(locale: Locale = Locale.getDefault()): String? {
    return this.toCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, locale)
}

/**
 * Returns the year component of the date.
 * @return The year component.
 */
fun Date.year(): Int {
    return this.toCalendar().get(Calendar.YEAR)
}

/**
 * Returns the day of the month.
 * @return The day of the month.
 */
fun Date.day(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_MONTH)
}

/**
 * Returns the day of the week.
 * @return The day of the week.
 */
fun Date.dayOfWeek(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_WEEK)
}

/**
 * Returns the name of the day of the week.
 * @param locale The locale for the day of the week name. Default is the system locale.
 * @return The name of the day of the week.
 */
fun Date.dayOfWeekName(locale: Locale = Locale.getDefault()): String? {
    return this.toCalendar().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale)
}

/**
 * Returns the day of the year.
 * @return The day of the year.
 */
fun Date.dayOfYear(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_YEAR)
}

/**
 * Calculates the difference in years between two LocalDate objects.
 * @param other The other LocalDate object.
 * @return The absolute difference in years.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.yearsDifference(other: LocalDate): Long {
    return ChronoUnit.YEARS.between(this, other).absoluteValue
}

/**
 * Calculates the difference in months between two LocalDate objects.
 * @param other The other LocalDate object.
 * @return The absolute difference in months.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.monthsDifference(other: LocalDate): Long {
    return ChronoUnit.MONTHS.between(this, other).absoluteValue
}

/**
 * Calculates the difference in days between two LocalDate objects.
 * @param other The other LocalDate object.
 * @return The absolute difference in days.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.daysDifference(other: LocalDate): Long {
    return ChronoUnit.DAYS.between(this, other).absoluteValue
}