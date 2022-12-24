package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.GridItem

data class GameState(
    //*Initial State
    val playerSelection: MutableState<Option?> = mutableStateOf(Option.listOfOption[0]),
    val computerSelection: MutableState<Option?> = mutableStateOf(null),
    val versus: MutableState<String?> = mutableStateOf(null),


    //*Game State
    val currentTurn:MutableState<Boolean?> = mutableStateOf(false),
    val currentValueOne:MutableState<GridItem> = mutableStateOf(GridItem(number = "1",
                                                                        state = mutableStateOf(null))),
    val currentValueTwo:MutableState<GridItem> = mutableStateOf(GridItem(number = "2",
                                                                        state = mutableStateOf(null))),
    val currentValueThree:MutableState<GridItem> = mutableStateOf(GridItem(number = "3",
                                                                            state = mutableStateOf(null))),
    val currentValueFour:MutableState<GridItem> = mutableStateOf(GridItem(number = "4",
                                                                            state = mutableStateOf(null))),
    val currentValueFive:MutableState<GridItem> = mutableStateOf(GridItem(number = "5",
                                                                                state = mutableStateOf(null))),
    val currentValueSix:MutableState<GridItem> = mutableStateOf(GridItem(number = "6",
                                                                            state = mutableStateOf(null))),
    val currentValueSeven:MutableState<GridItem> = mutableStateOf(GridItem(number = "7",
                                                                            state = mutableStateOf(null))),
    val currentValueEight:MutableState<GridItem> = mutableStateOf(GridItem(number = "8",
                                                                            state = mutableStateOf(null))),
    val currentValueNine:MutableState<GridItem> = mutableStateOf(GridItem(number = "9",
                                                                            state = mutableStateOf(null))),
    val turn:MutableState<Option> = mutableStateOf(Option.listOfOption[0]),

    val playerWins: MutableState<Int> = mutableStateOf(0),
    val ties:MutableState<Int> = mutableStateOf(0),
    val rivalWins: MutableState<Int> = mutableStateOf(0),
    val reset:MutableState<Boolean> = mutableStateOf(false),

    val currentStatePlayerOne:MutableState<Boolean> = mutableStateOf(false),
    val currentStatePlayerTwo:MutableState<Boolean> = mutableStateOf(false),
    val currentStateTies:MutableState<Boolean> = mutableStateOf(false),

    val matchCompletedState: MutableState<Boolean> = mutableStateOf(false),

    //*currentState
    val currentState: MutableState<ScreenState> = mutableStateOf(ScreenState.InitialState)
)
