package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util

sealed class ScreenState{
    object InitialState:ScreenState()
    object MatchState:ScreenState()
    object PlayerOneWins:ScreenState()
    object PlayerTwoWins:ScreenState()
    object MatchTie: ScreenState()
}
