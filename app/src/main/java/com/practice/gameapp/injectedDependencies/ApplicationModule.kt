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
/*
    fun provideApi(): GameAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }
 */
    single{
        Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }

    fun provideGameClient(gameAPI: GameAPI): GameClient {
        return GameAPIClient(gameAPI)
    }

    fun provideGameRepository(gameClient: GameAPIClient): GameRepository {
        return GameAPIRepository(gameClient)
    }

    //single { provideApi() }
    single { provideGameClient(get()) }
    single { provideGameRepository(get()) }

    //DATABASE

    fun provideRoomKoin(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            GameAppDataBase::class.java,
            "game_app_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    fun provideScoreDaoKoin(db: GameAppDataBase) = db.getScoreDao()

    fun provideScoreRepositoryKoin(scoreDao: ScoreDao): ScoreRepository {
        return ScoreRepositoryImpl(scoreDao)
    }

    fun provideGameDaoKoin(db: GameAppDataBase) = db.getGameDao()

    fun provideGameDBRepositoryKoin(gameDao: GameDao): GameDBRepository {
        return GameDBRepositoryImpl(gameDao)
    }

    fragment { HomeFragment() }
    fragment { QuizGameFragment() }
    fragment { QuizMenuFragment() }
    fragment { ScoreFragment() }
    fragment { MenuGameVersus() }
    fragment { VersusFragment() }

    single { provideRoomKoin(MainApplication()) }
    single { provideScoreDaoKoin(get()) }
    single { provideScoreRepositoryKoin(get()) }
    single { provideGameDaoKoin(get()) }
    single { provideGameDBRepositoryKoin(get()) }

    //ViewModel
    viewModel { HomeViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { ScoreViewModel(get()) }
    viewModel { VersusViewModel() }
}