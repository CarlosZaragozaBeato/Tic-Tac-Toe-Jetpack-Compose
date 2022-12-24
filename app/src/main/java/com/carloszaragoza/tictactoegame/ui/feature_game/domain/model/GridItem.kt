package com.carloszaragoza.tictactoegame.ui.feature_game.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class GridItem (
    val number:String,
    var state: MutableState<Boolean?> = mutableStateOf(null))