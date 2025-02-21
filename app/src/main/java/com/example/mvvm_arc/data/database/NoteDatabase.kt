package com.example.mvvm_arc.data.database

import androidx.room.Database
import com.example.mvvm_arc.data.dao.NoteDao
import com.example.mvvm_arc.data.entity.NoteEntity
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [NoteEntity :: class]
)
abstract class NoteDatabase : RoomDatabase() {

    abstract  val dao : NoteDao

    companion object{
        const val name = "note_db"
    }
}