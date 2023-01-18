package br.com.devcapu.beehealthy.food.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.common.data.datasource.LocalFoodDataSource
import br.com.devcapu.beehealthy.common.data.repository.FoodRepository
import br.com.devcapu.beehealthy.common.domain.model.Food
import br.com.devcapu.beehealthy.food.add.state.AddFoodUiState
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddFoodViewModel(
    private val foodRepository: FoodRepository,
) : ViewModel() {

    private var _state = MutableStateFlow(AddFoodUiState())
    val state: StateFlow<AddFoodUiState> = _state

    init {
        _state.value = _state.value.copy(
            onSearchedFoodChange = {
                _state.value = _state.value.copy(searchedFood = it)
            }
        )
    }

    fun findAllFoods(jsonFromAssets: String) {
        _state.value = _state.value.copy(
            foodListUiState = foodRepository.getAll(jsonFromAssets).map {
                FoodUiState(
                    id = it.id.toInt(),
                    name = it.description,
                    measure = "${it.base_qty} + ${it.base_unit}",
                )
            }
        )
    }

    fun addFood(food: FoodUiState) {
        viewModelScope.launch {
            foodRepository.addFood(
                food = Food(
                    id = food.id.toString(),
                    description = food.name,
                    base_qty = "100",
                    base_unit = "g"
                ),
                quantity = _state.value.addFoodBottomSheetUiState.quantity.toInt()
            )
        }
    }

    class Factory(
        private val foodRepository: FoodRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddFoodViewModel(foodRepository = foodRepository) as T
        }
    }
}