package br.com.devcapu.beehealthy.food.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.common.data.datasource.LocalFoodDataSource
import br.com.devcapu.beehealthy.common.data.repository.FoodRepository
import br.com.devcapu.beehealthy.food.add.state.AddFoodUiState
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
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

    fun findAllFoods(jsonFromAssets: String) {
        _state.value = _state.value.copy(
            foodListUiState = FoodRepository(LocalFoodDataSource()).getAll(jsonFromAssets).map {
                FoodUiState(
                    name = it.description,
                    measure = "${it.base_qty} + ${it.base_unit}",
                )
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