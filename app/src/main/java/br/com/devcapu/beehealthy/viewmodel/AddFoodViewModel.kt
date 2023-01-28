package br.com.devcapu.beehealthy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.repository.FoodRepository
import br.com.devcapu.beehealthy.repository.MealRepository
import br.com.devcapu.beehealthy.model.Food
import br.com.devcapu.beehealthy.uistate.UiState
import br.com.devcapu.beehealthy.food.add.state.AddFoodUiState
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddFoodViewModel(
    private val foodRepository: FoodRepository,
    private val mealRepository: MealRepository,
) : ViewModel() {

    private var _state = MutableStateFlow(AddFoodUiState())
    val state: StateFlow<AddFoodUiState> = _state

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        _state.value = _state.value.copy(
            onSearchedFoodChange = { searchingFood ->
                _uiState.value = UiState.Success(
                    _state.value.copy(
                        searchedFood = searchingFood,
                        searchedFoodUiState = _state.value.allFoodsUiState.filter {
                            it.name.contains(
                                searchingFood,
                                ignoreCase = true
                            )
                        }
                    )
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

    fun find(mealId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            try {
                val meal = mealRepository.findMeal(id = mealId)
                _state.value = _state.value.copy(meal = meal.name)
                _uiState.value = UiState.Success(_state.value)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message)
            }
        }
    }

    class Factory(
        private val foodRepository: FoodRepository,
        private val mealRepository: MealRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddFoodViewModel(
                foodRepository = foodRepository,
                mealRepository = mealRepository,
            ) as T
        }
    }
}