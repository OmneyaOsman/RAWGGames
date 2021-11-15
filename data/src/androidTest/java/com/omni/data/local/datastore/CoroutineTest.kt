package com.omni.data.local.datastore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
abstract class CoroutineTest {

    protected val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setupViewModelScope() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanupViewModelScope() {
        Dispatchers.resetMain()
    }

    @After
    fun cleanupCoroutines() {
        testDispatcher.cleanupTestCoroutines()
        testDispatcher.resumeDispatcher()
    }

    fun coTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest(block)
}