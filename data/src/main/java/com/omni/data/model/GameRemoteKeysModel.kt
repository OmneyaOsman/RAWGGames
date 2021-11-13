package com.omni.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class GameRemoteKeysModel(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    var gener: String,
    val nextPageKey: String?
)
