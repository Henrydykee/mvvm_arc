package com.example.mvvm_arc.domain.repositiory

import com.example.mvvm_arc.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
    suspend fun deleteNote(note: Note)
     fun getAllNotes(): Flow<List<Note>>
    suspend fun updateNote(note: Note)

}