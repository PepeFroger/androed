package com.example.lab1_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.lab1_2.ui.quiz.QuizScreen
import com.example.lab1_2.ui.screen.ShopScreen
import com.example.lab1_2.ui.theme.ShopTheme

enum class Screen { SHOP, QUIZ }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopTheme {
                var screen by remember { mutableStateOf(Screen.SHOP) }

                when (screen) {
                    Screen.SHOP -> ShopScreen(
                        onOpenQuiz = { screen = Screen.QUIZ }
                    )
                    Screen.QUIZ -> QuizScreen(
                        onBackToHome = { screen = Screen.SHOP }
                    )
                }
            }
        }
    }
}