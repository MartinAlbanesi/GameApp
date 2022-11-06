package com.practice.gameapp.injectedDependencies

import android.content.Context
import androidx.room.Room
import com.practice.gameapp.data.repositories.database.GameAppDataBase
import com.practice.gameapp.data.repositories.database.dao.GameDao
import com.practice.gameapp.data.repositories.database.repository.GameDBRepository
import com.practice.gameapp.data.repositories.database.repository.GameDBRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SCORE_DATABASE_NAME = "score_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            GameAppDataBase::class.java,
            SCORE_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideScoreDao(db : GameAppDataBase) = db.getScoreDao()

    @Singleton
    @Provides
    fun provideGameDao(db :GameAppDataBase) = db.getGameDao()

     @Singleton
     @Provides
    fun provideGameDBRepository(gameDao: GameDao):GameDBRepository{
        return GameDBRepositoryImpl(gameDao)
    }
}