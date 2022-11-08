package com.practice.gameapp.injectedDependencies

import android.content.Context
import androidx.room.Room
import com.practice.gameapp.data.repositories.database.GameAppDataBase
import com.practice.gameapp.data.repositories.database.dao.GameDao
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.data.repositories.database.repository.GameDBRepository
import com.practice.gameapp.data.repositories.database.repository.GameDBRepositoryImpl
import com.practice.gameapp.data.repositories.database.repository.ScoreRepository
import com.practice.gameapp.data.repositories.database.repository.ScoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val GAME_APP_DATABASE_NAME = "game_app_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            GameAppDataBase::class.java,
            GAME_APP_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideScoreDao(db: GameAppDataBase) = db.getScoreDao()

    @Singleton
    @Provides
    fun provideScoreRepository(scoreDao: ScoreDao): ScoreRepository {
        return ScoreRepositoryImpl(scoreDao)
    }

    @Singleton
    @Provides
    fun provideGameDao(db: GameAppDataBase) = db.getGameDao()

    @Singleton
    @Provides
    fun provideGameDBRepository(gameDao: GameDao): GameDBRepository {
        return GameDBRepositoryImpl(gameDao)
    }
}