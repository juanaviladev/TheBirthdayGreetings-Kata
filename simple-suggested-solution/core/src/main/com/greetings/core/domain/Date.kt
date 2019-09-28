package com.greetings.core.domain

import java.util.*
import java.util.Date as PlatformDate

class Date(private val day : Int,
           private val month : Int,
           private val year: Int) {

    private val internalDate : PlatformDate

    init {
        val cal = Calendar.getInstance()

        cal.set(year,month,day)

        internalDate = cal.time
    }

    fun sameDayAndMonthAs(other: Date): Boolean {
        return other.day == day && other.month == month
    }


    fun day(): Int = day

    fun year(): Int = year

    fun month(): Int = month

    companion object {
        fun today() : Date {
            val cal = Calendar.getInstance()

            val day = cal[Calendar.DAY_OF_MONTH]
            val month = cal[Calendar.MONTH] + 1
            val year = cal[Calendar.YEAR]

            return Date(day, month, year)
        }
    }

}