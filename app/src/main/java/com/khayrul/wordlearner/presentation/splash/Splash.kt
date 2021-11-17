package com.khayrul.wordlearner.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.khayrul.wordlearner.R
import com.khayrul.wordlearner.presentation.util.Screen
import kotlinx.coroutines.delay

@Composable
fun Splash(
    navController: NavController
) {
    val scale = remember {
        Animatable(.3f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = .7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screen.WordsScreen.route);
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.abc),
            contentDescription = "Splash image",
            modifier = Modifier.scale(scale = scale.value)
        )
    }
}