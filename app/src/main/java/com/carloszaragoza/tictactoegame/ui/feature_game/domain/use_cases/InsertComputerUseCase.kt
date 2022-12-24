package com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases

import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option.Companion.listOfOption

class InsertComputerUseCase {
    operator fun invoke(value:String):Option{
        return when(value){
            "X" -> listOfOption[1]
            "O" -> listOfOption[0]
            else -> listOfOption[0]
        }
    }
}