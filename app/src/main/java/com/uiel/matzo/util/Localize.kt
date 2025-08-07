package com.uiel.matzo.util

import com.uiel.matzo.R
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatKoreanDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("M월 d일 E요일", Locale.KOREAN)
    return date.format(formatter)
}

fun MealType.toKorean(): String {
    return when (this) {
        MealType.BREAKFAST -> "아침"
        MealType.LUNCH -> "점심"
        MealType.DINNER -> "저녁"
    }
}

fun WorkingType.toKorean(): String {
    return when(this) {
        WorkingType.OVER -> "초과 근무"
        WorkingType.REGULAR -> "일반 근무"
        WorkingType.OUTSIDER -> "외부인"
    }
}

fun MealType.toIcon(): Int {
    return when (this) {
        MealType.BREAKFAST -> R.drawable.ic_sun_set
        MealType.LUNCH -> R.drawable.ic_sun
        MealType.DINNER -> R.drawable.ic_moon
    }
}