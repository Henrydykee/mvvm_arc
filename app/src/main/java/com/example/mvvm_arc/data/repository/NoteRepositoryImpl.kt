package com.example.mvvm_arc.data.repository
import com.example.mvvm_arc.data.dao.NoteDao
import com.example.mvvm_arc.data.mapper.asExternalModel
import com.example.mvvm_arc.data.mapper.toEntity
import com.example.mvvm_arc.domain.model.Note
import com.example.mvvm_arc.domain.repositiory.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// Implementation of the NoteRepository interface using NoteDao for database operations
class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {

    // Inserts a note into the database by converting it to an entity format
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    // Retrieves a note by its ID, converting the database entity back to a Note model
    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.asExternalModel()
    }

    // Deletes a note from the database after converting it to an entity
    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note.toEntity())
    }

    // Fetches all notes from the database as a Flow (for real-time updates)
    // Converts each note entity from the database into a Note model
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { notes ->
            notes.map { it.asExternalModel() }
        }
    }

    // Updates an existing note in the database by converting it to an entity format
    override suspend fun updateNote(note: Note) {
        return dao.updateNote(note.toEntity())
    }
}
