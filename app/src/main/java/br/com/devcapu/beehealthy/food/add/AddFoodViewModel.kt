package br.com.devcapu.beehealthy.food.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.common.data.repository.FoodRepository
import br.com.devcapu.beehealthy.common.domain.model.Food
import br.com.devcapu.beehealthy.food.add.state.AddFoodUiState
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
import kotlinx.coroutines.Dispatchers
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
            onSearchedFoodChange = { searchingFood ->
                _state.value = _state.value.copy(
                    searchedFood = searchingFood,
                    searchedFoodUiState = _state.value.allFoodsUiState.filter {
                        it.name.contains(
                            searchingFood,
                            ignoreCase = true
                        )
                    }
                )
            },
        )
    }

    fun findAllFoods(jsonFromAssets: String) {
        _state.value = _state.value.copy(
            allFoodsUiState = foodRepository.getAll(jsonFromAssets).map {
                FoodUiState(
                    id = it.id.toInt(),
                    name = it.description,
                    measure = "${it.base_qty} + ${it.base_unit}",
                    calories = it.attributes.calories,
                    carbohydrates = it.attributes.carbohydrate?.qty ?: "0",
                    proteins = it.attributes.protein?.qty ?: "0",
                    fats = it.attributes.lipid?.qty ?: "0"
                )
            }
        )
    }

    fun selectedFood(it: FoodUiState) {
        _state.value = _state.value.copy(
            selectedFood = it
        )
    }

    fun addFood(food: FoodUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            foodRepository.addFood(
                food = Food(
                    id = food.id.toString(),
                    description = food.name,
                    base_qty = "100",
                    base_unit = "g",
                    attributes = Food.Attributes(
                        carbohydrate = Food.Attributes.Bla(food.carbohydrates),
                        protein = Food.Attributes.Bla(food.carbohydrates),
                        lipid = Food.Attributes.Bla(food.carbohydrates),
                        energy = Food.Attributes.Energy(food.calories),
                    )
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