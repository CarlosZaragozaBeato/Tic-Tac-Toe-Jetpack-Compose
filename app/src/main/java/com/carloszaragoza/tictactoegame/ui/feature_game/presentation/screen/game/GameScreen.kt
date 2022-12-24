package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.*
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameEvents
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.ScreenState
import com.carloszaragoza.tictactoegame.ui.theme.LocalSpacing
import com.carloszaragoza.tictactoegame.ui.theme.dark
import com.carloszaragoza.tictactoegame.ui.theme.grey

@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {


    Scaffold(
        topBar = {
            if(viewModel.state.currentState.value == ScreenState.MatchState)
                MatchTopBar(currentTurn = viewModel.state.turn.value){
                    viewModel.onEvent(GameEvents.OpenDialog) }
        }
    ){
        when(viewModel.state.currentState.value){
             ScreenState.InitialState -> InitialSt(viewModel = viewModel)
             ScreenState.MatchState -> MatchSt(viewModel = viewModel)
             ScreenState.PlayerTwoWins -> PlayerTwoWinsSt(viewModel = viewModel)
             ScreenState.PlayerOneWins -> PlayerOneWinsSt(viewModel = viewModel)
             ScreenState.MatchTie -> MathTieSt(viewModel = viewModel)
        }
    }
}

@Composable
fun MatchTopBar(currentTurn: Option?,
                openDialog:() -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = dark.copy(.5f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small)
        ){
            MatchTurn(currentTurn = currentTurn!!)

            RestartButton { openDialog.invoke() }
        }
    }
}


@Composable
fun MatchTurn(currentTurn:Option?) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        if(currentTurn != null){
            Image(painter = painterResource(id = currentTurn.image),
                contentDescription = currentTurn.type,
                modifier = Modifier.size(20.dp))
            Text("Turn".uppercase(),
                color = grey)
        }

    }
}

@Composable
fun RestartButton(action:() -> Unit) {
    IconButton(onClick = { action.invoke()},
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(grey)
            .padding(LocalSpacing.current.small)) {
        Icon(imageVector = Icons.Default.Refresh,
            contentDescription = "Restart Match",
            tint = MaterialTheme.colors.background,
            modifier = Modifier.size(30.dp))
    }
}