package com.example.lab1_2.data.model

import androidx.annotation.DrawableRes

enum class QuestionType {
    MULTIPLE_CHOICE,
    FREE_TEXT,
    IMAGE_FREE_TEXT
}

data class QuizQuestion(
    val id: Int,
    val type: QuestionType,
    val text: String,
    val options: List<String> = emptyList(),
    val correctAnswers: List<String>,
    @DrawableRes val imageRes: Int? = null
)