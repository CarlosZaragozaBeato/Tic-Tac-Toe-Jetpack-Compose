package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.GameViewModel
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameEvents
import com.carloszaragoza.tictactoegame.ui.theme.*

@Composable
fun InitialSt(viewModel:GameViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PickPlayer(viewModel.state.playerSelection.value?.type.toString()){ option ->
            viewModel.onEvent(GameEvents.SelectPlayerOne(option))
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.large))
        Column() {
            InitialStButton(text="New Game (VS CPU)",
                color = x.toArgb()){
                viewModel.onEvent(GameEvents.ChangeGameState("computer"))
            }
            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            InitialStButton(text = "New Game (Vs Player)",
                color = circle.toArgb()){
                viewModel.onEvent(GameEvents.ChangeGameState("player"))
            }
        }
    }
}



@Composable
fun PickPlayer(currenSelected:String,
                changePlayer:(Option) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(200.dp)
            .background(dark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Pick player 1's Mark".uppercase(),
            style = MaterialTheme.typography.h5)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .clip(RoundedCornerShape(5.dp))
                .height(80.dp)
                .padding(vertical = LocalSpacing.current.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Option.listOfOption.forEach { option ->
                BoxSelection(currentPlayer = currenSelected,
                                options = option,
                                changePlayer)
             }
            }
        Text("Remember - x goes first".uppercase(),
            style = MaterialTheme.typography.body1,
            color = grey)
        }
    }

@Composable
fun BoxSelection(currentPlayer: String?, options: Option, changePlayer: (Option) -> Unit) {

    if(currentPlayer!=null){
        if(options.type == currentPlayer){
            BoxSelected(options,
                        changePlayer)
        }else{
            BoxUnselected(options,
                        changePlayer)
        }
    }else{
        BoxUnselected(options, changePlayer)
    }
}


@Composable
fun BoxSelected(option: Option, changePlayer: (Option) -> Unit) {
    Box(modifier = Modifier
        .clickable { changePlayer.invoke(option) }
        .width(100.dp)
        .clip(RoundedCornerShape(LocalSpacing.current.small))
        .background(text),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = option.image),
            contentDescription = option.type,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit)
    }
}
@Composable
fun BoxUnselected(option: Option, changePlayer: (Option) -> Unit) {
    Box(modifier = Modifier
        .clickable { changePlayer.invoke(option) }
        .width(100.dp)
        .clip(RoundedCornerShape(LocalSpacing.current.small))
        .background(Color.Transparent),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = option.image),
            contentDescription = option.type,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit)
    }
}


@Composable
fun InitialStButton(text:String,
                    color:Int,
                    action:() -> Unit) {
    Button(onClick = { action.invoke() },
        shape = RectangleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(color),
            contentColor = MaterialTheme.colors.background),
        modifier = Modifier
            .fillMaxWidth(.7f)
            .height(50.dp)) {
        Text(text)
    }


}