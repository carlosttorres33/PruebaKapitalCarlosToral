package com.carlostorres.pruebakapitalcarlostoral.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.GetAllCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        getAllCards()
    }

    private fun getAllCards() = viewModelScope.launch(Dispatchers.IO) {
        _state.value = MainState(isLoading = true)
        try {
            val cardsList = getAllCardsUseCase()
            if (cardsList.isNotEmpty()) {
                _state.value = MainState(cards = cardsList, isLoading = false)
                Log.d("MainViewModel", "Cards: $cardsList")
            } else {
                _state.value = MainState(error = "No se encontraron cards", isLoading = false)
            }
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error al obtener las cards", e)
            _state.value = MainState(error = e.message ?: "Error desconocido", isLoading = false)
        }
    }

}