package com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases

class PlaverOCheckUseCase {

    operator fun invoke(option1: Boolean?, option2: Boolean?, option3: Boolean?): Boolean {
        return if (option1 != null && option2 != null && option3 != null) {
            option1 == false && option2 == false && option3 == false
        } else {
            false
        }
    }
}