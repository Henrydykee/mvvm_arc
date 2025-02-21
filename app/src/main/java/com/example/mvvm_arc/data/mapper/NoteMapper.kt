package com.example.mvvm_arc.data.mapper

import com.example.mvvm_arc.data.entity.NoteEntity
import com.example.mvvm_arc.domain.model.Note

fun NoteEntity.asExternalModel() : Note = Note(
    id,title,description
)

fun  Note.toEntity(): NoteEntity = NoteEntity(
    id,title,description
)