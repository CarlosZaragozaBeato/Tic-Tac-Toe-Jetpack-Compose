package com.carloszaragoza.tictactoegame.di

import com.carloszaragoza.tictactoegame.ui.feature_game.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideGameUseCase(): GameUseCases {
        return GameUseCases(
            insertPlayer = InsertComputerUseCase(),
            playerO = PlaverOCheckUseCase(),
            playerX = PlaverXCheckUseCase(),
            changeTurn = ChangeTurnUseCase()
        )
    }

}