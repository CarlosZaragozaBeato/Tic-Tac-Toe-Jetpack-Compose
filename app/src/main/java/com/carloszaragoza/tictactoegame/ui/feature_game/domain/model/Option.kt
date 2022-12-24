package com.carloszaragoza.tictactoegame.ui.feature_game.domain.model

import androidx.compose.ui.graphics.toArgb
import com.carloszaragoza.tictactoegame.R
import com.carloszaragoza.tictactoegame.ui.theme.circle
import com.carloszaragoza.tictactoegame.ui.theme.x

class Option(
    val type:String,
    val image:Int,
    val color: Int){
     companion object{
         val listOfOption:List<Option> = listOf(
             Option(type = "X",
                    image = R.drawable.x,
                    color = x.toArgb()),
             Option(type = "O",
                    image = R.drawable.circle,
                    color = circle.toArgb()),
         )
     }
 }
