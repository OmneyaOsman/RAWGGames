package com.omni.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.omni.data.local.db.dao.GameDao
import com.omni.data.model.GameModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class GameDaoTest {

    private lateinit var dao: GameDao
    private lateinit var db: RawgGamesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RawgGamesDatabase::class.java).build()
        dao = db.gamesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    fun whenInsertGamesThenRetrieveListOfGames() = runBlocking {
        //Arrange
        val list = listOf(
            GameModel(1, "", "The Witcher 3: Wild Hunt", 4.67),
            GameModel(2, "", "Grand Theft Auto V", 4.48)
        )
        list.takeIf { it.isNotEmpty() }
            ?.let { dao.insertAll(it) }


        //Act
        val retrievedList = dao.getGames()

        //Assert
        MatcherAssert.assertThat(retrievedList, CoreMatchers.`is`(CoreMatchers.notNullValue()))
        MatcherAssert.assertThat(retrievedList, IsEqual(list))
    }

    @Test
    fun whenDeleteGamesThenRetrieveListOfGamesIsEmpty() = runBlocking {
        //Arrange
        val list = listOf(
            GameModel(1, "", "The Witcher 3: Wild Hunt", 4.67),
            GameModel(2, "", "Grand Theft Auto V", 4.48)
        )
        list.takeIf { it.isNotEmpty() }
            ?.let { dao.insertAll(it) }


        //Act
        dao.deleteGames()
        val retrievedList = dao.getGames()

        //Assert
        MatcherAssert.assertThat(retrievedList, CoreMatchers.`is`(CoreMatchers.notNullValue()))
        MatcherAssert.assertThat(retrievedList, CoreMatchers.not(list))
        MatcherAssert.assertThat(retrievedList, IsEqual(emptyList()))
    }
}