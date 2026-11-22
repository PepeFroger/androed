package com.example.lab1_2.ui.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lab1_2.model.QuizQuestion
import com.example.lab1_2.model.QuizRepository

class QuizViewModel : ViewModel() {

    val questions: List<QuizQuestion> = QuizRepository.questions

    var currentIndex by mutableIntStateOf(0)
        private set

    private val _answers = mutableStateMapOf<Int, Any>()
    val answers: Map<Int, Any> get() = _answers

    var isFinished by mutableStateOf(false)
        private set

    var correctCount by mutableIntStateOf(0)
        private set

    val currentQuestion: QuizQuestion
        get() = questions[currentIndex]


    fun setMultiChoiceAnswer(questionId: Int, selected: Set<String>) {
        _answers[questionId] = selected
    }

    fun setFreeTextAnswer(questionId: Int, text: String) {
        _answers[questionId] = text
    }


    val hasNext: Boolean get() = currentIndex < questions.lastIndex

    fun goNext() {
        if (hasNext) {
            currentIndex++
        } else {
            finish()
        }
    }

    private fun finish() {
        correctCount = questions.count { q ->
            val userAnswer = _answers[q.id]
            when (q.type) {
                com.example.lab1_2.model.QuestionType.MULTIPLE_CHOICE -> {
                    val selected = userAnswer as? Set<*> ?: emptySet<Any>()
                    selected.map { it.toString() }.toSet() == q.correctAnswers.toSet()
                }
                else -> {
                    val text = (userAnswer as? String)?.trim()?.lowercase() ?: ""
                    q.correctAnswers.any { it.trim().lowercase() == text }
                }
            }
        }
        isFinished = true
    }

    fun reset() {
        currentIndex = 0
        _answers.clear()
        isFinished = false
        correctCount = 0
    }
}