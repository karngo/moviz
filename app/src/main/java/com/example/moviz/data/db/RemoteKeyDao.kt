package com.example.moviz.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: RemoteKey)

    @Query("SELECT * FROM remoteKeys WHERE movieId = :key")
    suspend fun remoteKeyByKey(key: Long): RemoteKey?

    @Query("DELETE FROM remoteKeys")
    suspend fun clearRemoteKeys()
}

