package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.GridItem
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.GameViewModel
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components.utils.ResetDialog
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameEvents
import com.carloszaragoza.tictactoegame.ui.theme.*

@Composable
fun MatchSt(viewModel: GameViewModel) {

    if(viewModel.state.computerSelection.value != null){

        if(viewModel.state.reset.value){
            Box(modifier = Modifier
                .background(dark.copy(.5f))
                .zIndex(4f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center){
                ResetDialog(restart = {
                    viewModel.onEvent(GameEvents.ResetGame)
                }){ viewModel.onEvent(GameEvents.OpenDialog) }
            }
        }

        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            GridMatch(viewModel = viewModel)
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridMatch(
    viewModel: GameViewModel) {
    LazyVerticalGrid(cells = GridCells.Fixed(3),
        modifier = Modifier
            .padding(LocalSpacing.current.small)
            .fillMaxHeight(.8f)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        items(viewModel.listOfOptionsSelected.value.size){ indx->
            BoxGame(option = viewModel.listOfOptionsSelected.value[indx].value,
                changeTurn = {viewModel.changeTurnOption(it)}){

                        when(viewModel.listOfOptionsName.value[indx]){
                            viewModel.listOfOptionsName.value[0] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnOne)
                            viewModel.listOfOptionsName.value[1] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnTwo)
                            viewModel.listOfOptionsName.value[2] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnThree)
                            viewModel.listOfOptionsName.value[3] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnFour)
                            viewModel.listOfOptionsName.value[4] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnFive)
                            viewModel.listOfOptionsName.value[5] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnSix)
                            viewModel.listOfOptionsName.value[6] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnSeven)
                            viewModel.listOfOptionsName.value[7] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnEight)
                            viewModel.listOfOptionsName.value[8] -> viewModel.onEvent(GameEvents.ChangeCurrentTurnNine)

                    }

            }
        }
    }
}

@Composable
fun BoxGame(option: GridItem,
            changeTurn:(Boolean?) -> Option?,
            action:()->Unit,
            ) {


    val currentTurn:Option? = changeTurn(option.state.value)


    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(LocalSpacing.current.small)
            .clickable { action.invoke() }
            .background(dark.copy(.5f))){

        if(currentTurn!=null){
            Image(painter = painterResource(id =currentTurn.image),
                contentDescription = currentTurn.type,
                modifier = Modifier.size(100.dp))
        }
    }
}


@Composable
fun ScoreScreen(
    playerOneWins:Int,
    playerOne:Option,
    ties:Int,
    playerTwoWins:Int,
    playerTwo:Option
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(.9f),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        ScorePanel(
            color = x.toArgb(),
            score = playerOneWins.toString(),
            type = playerOne.type + "(Player)"
        )
        ScorePanel(
            color = dark.toArgb(),
            score = ties.toString(),
            type = "Ties"
        )
        ScorePanel(
            color = circle.toArgb(),
            score = playerTwoWins.toString(),
            type = playerTwo.type + "(Rival)"
        )

    }

}

@Composable
fun ScorePanel(
    color:Int,
    score:String,
    type:String
){
    Column(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .width(100.dp)
        .height(60.dp)
        .background(Color(color)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(type,
            fontWeight = FontWeight.SemiBold,
            color =  Color.White)
        Text(score,
            fontWeight = FontWeight.Bold,
            color = grey)
    }
}
