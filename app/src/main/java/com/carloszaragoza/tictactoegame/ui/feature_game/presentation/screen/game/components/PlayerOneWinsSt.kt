package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.GameViewModel
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils.ButtonInitial
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils.Wins
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameEvents
import com.carloszaragoza.tictactoegame.ui.theme.LocalSpacing

@Composable
fun PlayerOneWinsSt(viewModel: GameViewModel) {

    Scaffold(
        topBar = { TopAppBar(backgroundColor = Color.Transparent,
                 elevation = 0.dp){
            Wins(image = Option.listOfOption[0].image,
                 title = "Player One Wins")
        } }) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            ButtonInitial(
                menu = {viewModel.onEvent(GameEvents.GoMenu)},
                restart = {viewModel.onEvent(GameEvents.ResetGame)})

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            ScoreScreen(
                playerOneWins = viewModel.state.playerWins.value,
                playerOne = viewModel.state.playerSelection.value!!,
                ties = viewModel.state.ties.value,
                playerTwoWins = viewModel.state.rivalWins.value,
                playerTwo =  viewModel.state.computerSelection.value!!,
            )
        }

    }
}

