package com.example.mvvm_arc.ui.screen.util

sealed interface UiEvent {
    data class Navigate (val route: String) : UiEvent
    data class ShowSnackbar (val message: String, val action: String? = null) : UiEvent
    object NavigateBack: UiEvent
}