package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carloszaragoza.tictactoegame.ui.theme.circle
import com.carloszaragoza.tictactoegame.ui.theme.x

@Composable
fun ButtonInitial(
    menu:() -> Unit,
    restart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.9f),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Button(onClick = { menu.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = x
            )) {
            Text(text = "Home")
        }
        Button(onClick = { restart.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = circle
            )) {
            Text("Restart")
        }
    }
}