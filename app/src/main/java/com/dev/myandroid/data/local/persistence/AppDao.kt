package com.dev.myandroid.data.local.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.myandroid.data.local.persistence.entity.User

@Dao
interface AppDao {
    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserId(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}