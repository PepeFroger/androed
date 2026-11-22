package com.example.lab1_2.data.repository

import com.example.lab1_2.R
import com.example.lab1_2.data.model.QuestionType
import com.example.lab1_2.data.model.QuizQuestion

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
            imageRes = R.drawable.camera
        ),
        QuizQuestion(
            id = 6,
            type = QuestionType.IMAGE_FREE_TEXT,
            text = "Как называется этот тип разъёма?",
            correctAnswers = listOf("usb"),
            imageRes = R.drawable.usb
        )
    )
}