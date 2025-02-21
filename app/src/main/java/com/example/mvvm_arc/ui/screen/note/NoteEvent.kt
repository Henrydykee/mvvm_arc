package com.example.mvvm_arc.ui.screen.note

sealed interface NoteEvent{
    data class  TitleChanged(val value: String) : NoteEvent;
    data class  DescriptionChanged(val value: String) : NoteEvent;
    object SaveNote : NoteEvent
    object NavigateBack : NoteEvent
}

