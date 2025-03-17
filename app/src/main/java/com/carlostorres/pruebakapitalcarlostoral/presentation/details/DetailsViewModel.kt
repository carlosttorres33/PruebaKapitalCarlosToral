package com.carlostorres.pruebakapitalcarlostoral.presentation.details

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.GetCardByIdUseCase
import com.carlostorres.pruebakapitalcarlostoral.domain.usecases.UpdateCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getCardByIdUseCase: GetCardByIdUseCase,
    private val updateCardUseCase: UpdateCardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state

    fun getCardById(cardId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _state.value = _state.value.copy(isLoading = true)
        try {
            val card = getCardByIdUseCase(cardId)
            _state.value = _state.value.copy(card = card)

        } catch (e: Exception) {
            _state.value = _state.value.copy(error = e.message)
        } finally {
            _state.value = _state.value.copy(isLoading = false)
        }

    }

    fun updateFavoriteCard() = viewModelScope.launch(Dispatchers.IO) {
        println()
        try {
            updateCardUseCase(
                _state.value.card!!.copy(isFavorite = !_state.value.card!!.isFavorite)
            )
            getCardById(cardId = _state.value.card!!.id)
        }catch (e:Exception){
            Toast.makeText(context, "Error al agregar a favoritos la carta", Toast.LENGTH_SHORT).show()
        }
    }

}