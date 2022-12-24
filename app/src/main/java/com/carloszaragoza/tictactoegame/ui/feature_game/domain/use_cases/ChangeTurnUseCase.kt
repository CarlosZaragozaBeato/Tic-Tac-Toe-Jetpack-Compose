package com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases

import androidx.compose.runtime.MutableState
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.GridItem

class ChangeTurnUseCase {

    operator fun invoke (currentValue: MutableState<GridItem>, currentTurn: MutableState<Boolean?>){

            currentTurn.value = !currentTurn.value!!
            currentValue.value.state.value = currentTurn.value!!

        }

}