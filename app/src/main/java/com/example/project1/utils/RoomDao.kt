package com.example.project1.utils

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


abstract class RoomDao {
    @Upsert
    abstract fun upsertNote(note: Note)

    @Delete
    abstract fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    abstract fun getNotes(): Flow<List<Note>>

}