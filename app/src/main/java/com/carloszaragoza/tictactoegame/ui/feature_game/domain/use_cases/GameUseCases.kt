package com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases

data class GameUseCases(
    val insertPlayer: InsertComputerUseCase,
    val playerX: PlaverXCheckUseCase,
    val playerO: PlaverOCheckUseCase,
    val changeTurn:ChangeTurnUseCase

)
