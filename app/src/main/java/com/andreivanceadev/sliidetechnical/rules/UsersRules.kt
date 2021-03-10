package com.andreivanceadev.sliidetechnical.rules

import com.andreivanceadev.sliidetechnical.repository.TimeRepository
import toothpick.InjectConstructor
import java.text.SimpleDateFormat
import java.util.*

@InjectConstructor
class UsersRules(private val timeRepository: TimeRepository) {

    fun getUserCreationTimeRelativeToNow(createdAt: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val createdDate = Calendar.getInstance().apply { time = sdf.parse(createdAt) ?: Date() }

        val dateNow = Calendar.getInstance().apply { timeInMillis = timeRepository.getCurrentTimeMs()}

        val timeFromCreation = dateNow.get(Calendar.DAY_OF_YEAR) - createdDate.get(Calendar.DAY_OF_YEAR)
        return "Created $timeFromCreation days ago"
    }
}