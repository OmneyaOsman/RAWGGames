package com.omni.domain.repository

import kotlinx.coroutines.flow.Flow

interface GenerePreferencesSource {
    suspend fun setGenere(genereId:String?)
    fun getGenere(): Flow<String>
}