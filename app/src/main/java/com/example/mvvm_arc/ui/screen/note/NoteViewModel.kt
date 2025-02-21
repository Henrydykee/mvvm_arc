package com.example.mvvm_arc.ui.screen.note
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_arc.domain.model.Note
import com.example.mvvm_arc.domain.repositiory.NoteRepository
import com.example.mvvm_arc.ui.screen.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor (
    private val repo : NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state : StateFlow<NoteState> = _state.asStateFlow();

    private  val  _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    fun sendEvent(event: UiEvent){
        viewModelScope.launch {
            _event.send(event)
        }
    }

    fun onEvent(event : NoteEvent){
        when(event){
            is NoteEvent.DescriptionChanged -> {
                _state.update {
                    it.copy(
                        description = event.value
                    )
                }
            }

            NoteEvent.NavigateBack -> {
                UiEvent.NavigateBack
            }
            NoteEvent.SaveNote -> {
                val state = _state.value
                viewModelScope.launch {
                    if(state.id == null){
                        repo.insertNote(
                            Note(
                                id = state.id,
                                title = state.title,
                                description = state.description
                            )
                        )
                    }else{
                        repo.updateNote(
                            Note(
                                id = state.id,
                                title = state.title,
                                description = state.description
                            )
                        )
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }
            is NoteEvent.TitleChanged -> {
                _state.update {
                    it.copy(
                        title = event.value
                    )
                }
            }

            NoteEvent.DeleteNote -> {
                val state = _state.value
                viewModelScope.launch {
                    repo.deleteNote(
                        Note(
                            id = state.id,
                            title = state.title,
                            description = state.description
                        )
                    )
                }
                sendEvent(UiEvent.NavigateBack)
            }
        }
    }
}