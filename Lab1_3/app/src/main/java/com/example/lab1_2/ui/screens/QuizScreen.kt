package com.example.lab1_2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1_2.data.model.QuestionType
import com.example.lab1_2.ui.components.quiz.FreeTextQuestion
import com.example.lab1_2.ui.components.quiz.ImageFreeTextQuestion
import com.example.lab1_2.ui.components.quiz.MultipleChoiceQuestion
import com.example.lab1_2.ui.viewmodels.QuizViewModel

@Composable
fun QuizScreen(
    onBackToHome: () -> Unit,
    viewModel: QuizViewModel = viewModel()
) {
    if (viewModel.isFinished) {
        QuizStatisticsScreen(
            correct = viewModel.correctCount,
            total = viewModel.questions.size,
            onBackToHome = {
                viewModel.reset()
                onBackToHome()
            }
        )
        return
    }

    val question = viewModel.currentQuestion

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Вопрос ${viewModel.currentIndex + 1} из ${viewModel.questions.size}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        when (question.type) {
            QuestionType.MULTIPLE_CHOICE -> {
                val selected = (viewModel.answers[question.id] as? Set<*>)?.map { it.toString() }?.toSet() ?: emptySet()
                MultipleChoiceQuestion(
                    question = question,
                    selected = selected,
                    onSelectionChange = { viewModel.setMultiChoiceAnswer(question.id, it) }
                )
            }
            QuestionType.FREE_TEXT -> {
                val answer = viewModel.answers[question.id] as? String ?: ""
                FreeTextQuestion(
                    question = question,
                    answer = answer,
                    onAnswerChange = { viewModel.setFreeTextAnswer(question.id, it) }
                )
            }
            QuestionType.IMAGE_FREE_TEXT -> {
                val answer = viewModel.answers[question.id] as? String ?: ""
                ImageFreeTextQuestion(
                    question = question,
                    answer = answer,
                    onAnswerChange = { viewModel.setFreeTextAnswer(question.id, it) }
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { viewModel.goNext() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (viewModel.hasNext) "Следующий вопрос" else "Завершить")
        }
    }
}

@Composable
private fun QuizStatisticsScreen(
    correct: Int,
    total: Int,
    onBackToHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Квиз завершён!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text("Правильных: $correct", fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))
        Text("Неправильных: ${total - correct}", fontSize = 18.sp)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onBackToHome) { Text("На главную") }
    }
}