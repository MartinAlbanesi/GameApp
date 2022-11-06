package com.practice.gameapp.injectedDependencies

import android.content.Context
import androidx.room.Room
import com.practice.gameapp.data.repositories.database.ScoreDataBase
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
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

    private const val SCORE_DATABASE_NAME = "score_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ScoreDataBase::class.java,
            SCORE_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideScoreRepository(scoreDao: ScoreDao):ScoreRepository{
        return ScoreRepositoryImpl(scoreDao)
    }

    @Singleton
    @Provides
    fun provideScoreDao(db : ScoreDataBase) = db.getScoreDao()

}