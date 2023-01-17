package br.com.devcapu.beehealthy.food.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.food.add.screen.AddFoodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddFoodViewModel : ViewModel() {

    private var _state = MutableStateFlow(AddFoodUiState())
    val state: StateFlow<AddFoodUiState> = _state

    init {
        _state.value = _state.value.copy(
            onSearchedFood = {
                _state.value = _state.value.copy(searchedFood = it)
            }
        )
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddFoodViewModel() as T
        }
    }
}