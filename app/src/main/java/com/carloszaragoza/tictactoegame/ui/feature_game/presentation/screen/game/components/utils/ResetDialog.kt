package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.carloszaragoza.tictactoegame.ui.theme.circle
import com.carloszaragoza.tictactoegame.ui.theme.dark
import com.carloszaragoza.tictactoegame.ui.theme.x

@Composable
fun ResetDialog(
    restart:() -> Unit,
    action:() -> Unit
) {
    Column(
        modifier = Modifier
            .background(dark)
            .zIndex(2f)
            .fillMaxWidth()
            .fillMaxHeight(.25f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text("Restart the game?",
                style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(25.dp))
            Row(){
               Button(onClick = { action.invoke() },
                   colors = ButtonDefaults.buttonColors(
                       backgroundColor = x
                   )) {
                   Text("No, Cancel")
               }
                Spacer(modifier = Modifier.width(25.dp))

                Button(onClick = { restart.invoke() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = circle
                    )) {
                    Text("Yes, Restart")
                }
            }
    }
}