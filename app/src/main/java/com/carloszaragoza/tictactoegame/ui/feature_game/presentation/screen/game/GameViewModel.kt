package com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.Option
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.model.GridItem
import com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases.GameUseCases
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameEvents
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.GameState
import com.carloszaragoza.tictactoegame.ui.feature_game.presentation.screen.game.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val useCase: GameUseCases
):ViewModel() {

    val state by mutableStateOf(GameState())




    val listOfOptionsSelected:MutableState<List<MutableState<GridItem>>> = mutableStateOf(listOf(
        state.currentValueOne,
        state.currentValueTwo,
        state.currentValueThree,
        state.currentValueFour,
        state.currentValueFive,
        state.currentValueSix,
        state.currentValueSeven,
        state.currentValueEight,
        state.currentValueNine))

    val listOfOptionsName:MutableState<List<String>> = mutableStateOf(listOf(
        "First",
        "Second",
        "Third",
        "Fourth",
        "Fifth",
        "Sixth",
        "Seventh",
        "Eighth",
        "Ninth",))

    fun onEvent(event:GameEvents){
        when(event){
            //*Initial Events
            is GameEvents.SelectPlayerOne -> selectPlayer(event.type)
            is GameEvents.ChangeGameState -> changeGameState(event.versus)
            //*Game State Events
            is GameEvents.ChangeCurrentTurnOne -> changeTurnOne()
            is GameEvents.ChangeCurrentTurnTwo -> changeTurnTwo()
            is GameEvents.ChangeCurrentTurnThree -> changeTurnThree()
            is GameEvents.ChangeCurrentTurnFour -> changeTurnFour()
            is GameEvents.ChangeCurrentTurnFive -> changeTurnFive()
            is GameEvents.ChangeCurrentTurnSix -> changeTurnSix()
            is GameEvents.ChangeCurrentTurnSeven -> changeTurnSeven()
            is GameEvents.ChangeCurrentTurnEight -> changeTurnEight()
            is GameEvents.ChangeCurrentTurnNine -> changeTurnNine()
            is GameEvents.OpenDialog -> openDialog()
            is GameEvents.ResetGame ->{
                state.currentState.value = ScreenState.MatchState
                resetGame()
            }
            is GameEvents.GoMenu -> goMenu()
        }
    }

    private fun goMenu(){
        viewModelScope.launch {
            resetGame()
            state.currentState.value = ScreenState.InitialState
        }
    }

    fun changeTurnOption(st:Boolean?):Option?{
        return if(st == null) {
              null
        } else if(st){
            state.turn.value = Option.listOfOption[1]
            Option.listOfOption[0]

        }else{
            state.turn.value = Option.listOfOption[0]
             Option.listOfOption[1]
        }
    }

    //*Reset
    private fun openDialog(){
        state.reset.value  = !state.reset.value
    }

    private fun resetGame(){
        viewModelScope.launch{
            listOfOptionsSelected.value.forEach{value ->
                value.value = GridItem(number = value.value.number,
                    state = mutableStateOf(null))
            }
            state.reset.value  = false
            state.currentStateTies.value = false
            state.currentStatePlayerOne.value = false
            state.currentStatePlayerTwo.value = false
            state.matchCompletedState.value = false
            state.currentTurn.value = false
            gameLogic()
        }
    }

    private fun changeGameState(versus:String){
       viewModelScope.launch {
           state.currentState.value = ScreenState.MatchState
           state.computerSelection.value = changeComputer(state.playerSelection.value!!)
           state.versus.value = versus

           gameLogic()
       }
    }

    private fun selectPlayer(value: Option) {
        state.playerSelection.value = value
        useCase.insertPlayer.invoke(value.type).let { option ->
            state.computerSelection.value = option
        }
    }

    private fun changeTurnOne(){
            useCase.changeTurn(state.currentValueOne, state.currentTurn)
            gameLogic()
    }
    private fun changeTurnTwo(){
            useCase.changeTurn(state.currentValueTwo, state.currentTurn)
            gameLogic()
    }
    private fun changeTurnThree(){
            useCase.changeTurn(state.currentValueThree, state.currentTurn)
            gameLogic()
    }
    private fun changeTurnFour(){
        useCase.changeTurn(state.currentValueFour, state.currentTurn)
        gameLogic()
    }
    private fun changeTurnFive(){
        useCase.changeTurn(state.currentValueFive, state.currentTurn)
        gameLogic()
    }
    private fun changeTurnSix(){
        useCase.changeTurn(state.currentValueSix, state.currentTurn)
        gameLogic()
    }
    private fun changeTurnSeven(){
        useCase.changeTurn(state.currentValueSeven, state.currentTurn)
        gameLogic()
    }
    private fun changeTurnEight(){
        useCase.changeTurn(state.currentValueEight, state.currentTurn)
        gameLogic()
    }
    private fun changeTurnNine(){
        useCase.changeTurn(state.currentValueNine, state.currentTurn)
        gameLogic()
    }

    private fun knowTheTurn(option: Boolean):Option{
    return if(option){
        Option.listOfOption[1]
    }else{
        Option.listOfOption[0]
    }
}

    private fun gameLogic(){
        when(state.versus.value){
            "computer" ->{
                vsComputer()
               }
            "player" -> {
                vsPlayer()
            }
        }
        viewModelScope.launch{
            matchState()
        }
    }

    private fun vsPlayer(){
        playerOneCheck()
        playerTwoCheck()
        completeCheck()
    }

    private fun vsComputer(){
        viewModelScope.launch {
            if(knowTheTurn( state.currentTurn.value!!).type == Option.listOfOption[0].type){
                if(state.computerSelection.value ==  Option.listOfOption[0]){
                    if(!gridFilled()){
                        botLogic()
                    }
                }
            }else{
                if(state.computerSelection.value ==  Option.listOfOption[1]){
                    if(!gridFilled()){
                        botLogic()
                    }
                }
            }
            playerOneCheck()
            playerTwoCheck()
            completeCheck()
        }
    }

    private fun botLogic(){
        val rnd: MutableState<GridItem?> = mutableStateOf(null)

        while(true){
            rnd.value = listOfOptionsSelected.value.random().value
            if(rnd.value!!.state.value == null){
                break
            }
        }
        changeTurnBot(rnd.value!!.number)
    }

    private fun changeTurnBot(number:String){
        when(number){
            "1" -> { useCase.changeTurn(state.currentValueOne, state.currentTurn) }
            "2" -> { useCase.changeTurn(state.currentValueTwo, state.currentTurn) }
            "3" -> { useCase.changeTurn(state.currentValueThree, state.currentTurn) }
            "4" -> { useCase.changeTurn(state.currentValueFour, state.currentTurn) }
            "5" -> { useCase.changeTurn(state.currentValueFive, state.currentTurn) }
            "6" -> { useCase.changeTurn(state.currentValueSix, state.currentTurn) }
            "7" -> { useCase.changeTurn(state.currentValueSeven, state.currentTurn) }
            "8" -> { useCase.changeTurn(state.currentValueEight, state.currentTurn) }
            "9" -> { useCase.changeTurn(state.currentValueNine, state.currentTurn) }
        }
    }

    private fun matchState(){
        if (!state.currentStatePlayerOne.value && !state.currentStatePlayerTwo.value ){
            if(state.currentStateTies.value){
                state.matchCompletedState.value = true
                state.ties.value +=1
               state.currentState.value = ScreenState.MatchTie
            }

        }else if(state.currentStatePlayerOne.value){
            state.matchCompletedState.value = true
            state.playerWins.value +=1
            state.currentState.value = ScreenState.PlayerOneWins
        }else if(state.currentStatePlayerTwo.value){
            state.rivalWins.value +=1
            state.matchCompletedState.value = true
            state.currentState.value = ScreenState.PlayerTwoWins
        }
    }

    private fun gridFilled():Boolean{
        var returnCondition = false

        for(i in 0 until listOfOptionsSelected.value.size){
            if(listOfOptionsSelected.value[i].value.state.value == null){
                returnCondition = false
                break
            }else{
                returnCondition = true
            }
        }
        return returnCondition
    }

    private fun completeCheck(){
        for(i in 0 until listOfOptionsSelected.value.size){
            if(listOfOptionsSelected.value[i].value.state.value == null){
                state.currentStateTies.value = false
                break
            }else{
                state.currentStateTies.value = true
            }
        }
    }

    private fun playerOneCheck(){
        viewModelScope.launch{

            if(useCase.playerX(listOfOptionsSelected.value[0].value.state.value
                    ,listOfOptionsSelected.value[1].value.state.value
                    ,listOfOptionsSelected.value[2].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[0].value.state.value
                    ,listOfOptionsSelected.value[1].value.state.value
                    ,listOfOptionsSelected.value[2].value.state.value)

            }else if( useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[6].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[6].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[0].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[1].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[7].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[1].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[7].value.state.value)

            }else if( useCase.playerX(listOfOptionsSelected.value[2].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[6].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[2].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[6].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[2].value.state.value,
                    listOfOptionsSelected.value[5].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[2].value.state.value,
                    listOfOptionsSelected.value[5].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[5].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[3].value.state.value,
                    listOfOptionsSelected.value[4].value.state.value,
                    listOfOptionsSelected.value[5].value.state.value)

            }else if(useCase.playerX(listOfOptionsSelected.value[6].value.state.value,
                    listOfOptionsSelected.value[7].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)){

                state.currentStatePlayerOne.value = useCase.playerX(listOfOptionsSelected.value[6].value.state.value,
                    listOfOptionsSelected.value[7].value.state.value,
                    listOfOptionsSelected.value[8].value.state.value)
            }
        }
    }

    private fun playerTwoCheck() {
        viewModelScope.launch {
            //*First Grid
            viewModelScope.launch {

                if (useCase.playerO(
                        listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[1].value.state.value,
                        listOfOptionsSelected.value[2].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[1].value.state.value,
                        listOfOptionsSelected.value[2].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[6].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[6].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[0].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[1].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[7].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[1].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[7].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[2].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[6].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[2].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[6].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[2].value.state.value,
                        listOfOptionsSelected.value[5].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[2].value.state.value,
                        listOfOptionsSelected.value[5].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)

                } else if (useCase.playerO(listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[5].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[3].value.state.value,
                        listOfOptionsSelected.value[4].value.state.value,
                        listOfOptionsSelected.value[5].value.state.value)

                } else if (useCase.playerO( listOfOptionsSelected.value[6].value.state.value,
                        listOfOptionsSelected.value[7].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)) {

                    state.currentStatePlayerTwo.value = useCase.playerO(
                        listOfOptionsSelected.value[6].value.state.value,
                        listOfOptionsSelected.value[7].value.state.value,
                        listOfOptionsSelected.value[8].value.state.value)
                }
            }
        }
    }

    private fun changeComputer(value:Option):Option{
       return if(value.type == "X"){
            Option.listOfOption[1]
        }else{
           Option.listOfOption[0]
       }
    }

}