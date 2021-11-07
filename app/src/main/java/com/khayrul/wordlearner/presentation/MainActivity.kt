package com.khayrul.wordlearner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.khayrul.wordlearner.presentation.add_edit_word.AddEditWordScreen
import com.khayrul.wordlearner.presentation.util.Screen
import com.khayrul.wordlearner.presentation.words.WordsScreen
import com.khayrul.wordlearner.ui.theme.WordLearnerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordLearnerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WordsScreen.route
                    ) {
                        composable(
                            route = Screen.WordsScreen.route,
                        ) {
                            WordsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditWordScreen.route +
                                    "?wordId={wordId}",
                            arguments = listOf(
                                navArgument(
                                    name = "wordId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditWordScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordLearnerTheme {
        Greeting("Android")
    }
}