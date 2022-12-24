package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util

import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option

sealed class GameEvents{
    data class SelectPlayerOne(val type :Option):GameEvents()
    data class ChangeGameState(val versus: String):GameEvents()
    object ChangeCurrentTurnOne:GameEvents()
    object ChangeCurrentTurnTwo:GameEvents()
    object ChangeCurrentTurnThree:GameEvents()
    object ChangeCurrentTurnFour:GameEvents()
    object ChangeCurrentTurnFive:GameEvents()
    object ChangeCurrentTurnSix:GameEvents()
    object ChangeCurrentTurnSeven:GameEvents()
    object ChangeCurrentTurnEight:GameEvents()
    object ChangeCurrentTurnNine:GameEvents()
    object OpenDialog:GameEvents()
    object ResetGame:GameEvents()
    object GoMenu:GameEvents()

}
