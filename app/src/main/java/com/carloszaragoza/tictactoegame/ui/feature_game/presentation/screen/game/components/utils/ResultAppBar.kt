package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.theme.LocalSpacing

@Composable
fun Wins(
    image:Int,
    title:String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(id = image),
            contentDescription = title)
        Text(title)
    }
}

@Composable
fun Draw() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(painter = painterResource(id = Option.listOfOption[0].image),
                contentDescription =  Option.listOfOption[0].type)

            Image(painter = painterResource(id = Option.listOfOption[1].image),
                contentDescription =  Option.listOfOption[1].type)
        }
        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
        Text("DRAW")
    }
}