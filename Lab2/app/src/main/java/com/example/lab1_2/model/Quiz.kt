package com.example.lab1_2.model

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


object QuizRepository {

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            type = QuestionType.MULTIPLE_CHOICE,
            text = "Какие из этих устройств являются смартфонами?",
            options = listOf("Galaxy X", "Ноутбук", "iPhone 15", "Телевизор"),
            correctAnswers = listOf("Galaxy X", "iPhone 15")
        ),
        QuizQuestion(
            id = 2,
            type = QuestionType.MULTIPLE_CHOICE,
            text = "Что входит в характеристики видеокарты?",
            options = listOf("Объём памяти", "Разрешение экрана", "Частота ядра", "Цвет корпуса"),
            correctAnswers = listOf("Объём памяти", "Частота ядра")
        ),
        QuizQuestion(
            id = 3,
            type = QuestionType.FREE_TEXT,
            text = "Сколько ГБ в 1 ТБ?",
            correctAnswers = listOf("1024")
        ),
        QuizQuestion(
            id = 4,
            type = QuestionType.FREE_TEXT,
            text = "Как называется устройство для хранения данных без движущихся частей?",
            correctAnswers = listOf("ssd")
        ),
        QuizQuestion(
            id = 5,
            type = QuestionType.IMAGE_FREE_TEXT,
            text = "Что изображено на картинке?",
            correctAnswers = listOf("камера"),
            imageRes = com.example.lab1_2.R.drawable.camera
        ),
        QuizQuestion(
            id = 6,
            type = QuestionType.IMAGE_FREE_TEXT,
            text = "Как называется этот тип разъёма?",
            correctAnswers = listOf("usb"),
            imageRes = com.example.lab1_2.R.drawable.usb
        )
    )
}