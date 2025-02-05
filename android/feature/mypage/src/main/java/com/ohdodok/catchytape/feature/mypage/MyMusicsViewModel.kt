package com.ohdodok.catchytape.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ohdodok.catchytape.core.domain.model.CtErrorType
import com.ohdodok.catchytape.core.domain.model.CtException
import com.ohdodok.catchytape.core.domain.model.Music
import com.ohdodok.catchytape.core.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

data class MyMusicsUiState(
    val myMusics: List<Music> = emptyList()
)

sealed interface MyMusicsEvent {
    data class ShowMessage(
        val error: CtErrorType
    ) : MyMusicsEvent
}

@HiltViewModel
class MyMusicsViewModel @Inject constructor(
    private val musicRepository: MusicRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyMusicsUiState())
    val uiState: StateFlow<MyMusicsUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<MyMusicsEvent>()
    val events: SharedFlow<MyMusicsEvent> = _events.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            val errorType =
                if (throwable is CtException) throwable.ctError
                else CtErrorType.UN_KNOWN

            _events.emit(MyMusicsEvent.ShowMessage(errorType))
        }
    }

    private val viewModelScopeWithExceptionHandler = viewModelScope + exceptionHandler

    init {
        fetchMyMusics()
    }

    private fun fetchMyMusics() {
        musicRepository.getMyMusics()
            .onEach { musics ->
                _uiState.update {
                    it.copy(myMusics = musics)
                }
            }
            .launchIn(viewModelScopeWithExceptionHandler)
    }
}