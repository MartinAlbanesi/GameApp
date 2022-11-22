package com.practice.gameapp.injectedDependencies

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.practice.gameapp.data.repositories.GameAPIRepository
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.data.repositories.database.GameAppDataBase
import com.practice.gameapp.data.repositories.database.dao.GameDao
import com.practice.gameapp.data.repositories.database.dao.ScoreDao
import com.practice.gameapp.data.repositories.database.repository.GameDBRepository
import com.practice.gameapp.data.repositories.database.repository.GameDBRepositoryImpl
import com.practice.gameapp.data.repositories.database.repository.ScoreRepository
import com.practice.gameapp.data.repositories.database.repository.ScoreRepositoryImpl
import com.practice.gameapp.data.repositories.network.game.GameAPI
import com.practice.gameapp.data.repositories.network.game.GameAPIClient
import com.practice.gameapp.data.repositories.network.game.GameClient
import com.practice.gameapp.ui.fragments.home.HomeFragment
import com.practice.gameapp.ui.fragments.quiz.QuizGameFragment
import com.practice.gameapp.ui.fragments.quiz.QuizMenuFragment
import com.practice.gameapp.ui.fragments.scores.ScoreFragment
import com.practice.gameapp.ui.fragments.versusGame.MenuGameVersus
import com.practice.gameapp.ui.fragments.versusGame.VersusFragment
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.practice.gameapp.ui.viewmodels.ScoreViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/*
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApi(): GameAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(gameClient: GameAPIClient): GameRepository {
        return GameAPIRepository(gameClient)
    }

    @Provides
    @Singleton
    fun provideGameClient(gameAPI: GameAPI): GameClient {
        return GameAPIClient(gameAPI)
    }
}

*/

val remoteRepositoryModule = module {
    //API

    fun provideApi(): GameAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }
/*
    fun provideGameClient(gameAPI: GameAPI): GameClient {
        return GameAPIClient(gameAPI)
    }
    fun provideGameRepository(gameClient: GameAPIClient): GameRepository {
        return GameAPIRepository(gameClient)
    }
 */

    single { provideApi() }
    single <GameClient> { GameAPIClient(get()) }
    single <GameRepository> { GameAPIRepository(get()) }


    //DATABASE
/*
    fun provideRoom(context: Context): GameAppDataBase {
        return Room.databaseBuilder(
            context,
            GameAppDataBase::class.java,
            "game_app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideScoreDao(db: GameAppDataBase) = db.getScoreDao()

    fun provideScoreRepository(scoreDao: ScoreDao): ScoreRepository {
        return ScoreRepositoryImpl(scoreDao)
    }

    fun provideGameDao(db: GameAppDataBase) = db.getGameDao()

    fun provideGameDBRepository(gameDao: GameDao): GameDBRepository {
        return GameDBRepositoryImpl(gameDao)
    }

    single { provideRoom(this.androidApplication()) }
    single { provideScoreDao(get()) }
    single { provideScoreRepository(get()) }
    single { provideGameDao(get()) }
    single { provideGameDBRepository(get()) }
*/

    //ViewModels
    single { HomeViewModel(get()) }
    single { QuizViewModel(get()) }
    //single { ScoreViewModel(get()) }
    single { VersusViewModel() }
}