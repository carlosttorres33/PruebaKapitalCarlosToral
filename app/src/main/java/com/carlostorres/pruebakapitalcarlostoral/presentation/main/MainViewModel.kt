package com.carlostorres.pruebakapitalcarlostoral.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.GetAllCardsUseCase
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.GetFavoriteCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getFavoriteCardsUseCase: GetFavoriteCardsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        setUpCards()
    }

    fun setUpCards(){
        getAllCards()
        getFavoriteCards()
    }

    private fun getFavoriteCards() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteCardsUseCase().collect {
            _state.value = _state.value.copy(favoriteCards = it)
            Log.d("MainViewModel", "Favorite Cards: $it")
        }
    }

    private fun getAllCards() = viewModelScope.launch(Dispatchers.IO) {
        _state.value = _state.value.copy(isLoading = true, error = "")

        try {
            val cards = getAllCardsUseCase()
            if (cards.isNotEmpty()) {
                _state.value = _state.value.copy(cards = cards, isLoading = false)
            }else{
                _state.value = _state.value.copy(error = "Error al obtener las cartas", isLoading = false)
            }
        }catch (e: Exception){
            Log.e("MainViewModel", "Error al obtener las cartas", e)
            _state.value = _state.value.copy(error = e.message ?: "Error desconocido", isLoading = false)
        }

    }

}