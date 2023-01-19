package br.com.devcapu.beehealthy.food.add.state

data class AddFoodUiState(
    val searchedFood: String = "",
    val onSearchedFoodChange: (String) -> Unit = { },
    val allFoodsUiState: List<FoodUiState> = emptyList(),
    val searchedFoodUiState: List<FoodUiState> = emptyList(),
    val addFoodBottomSheetUiState: AddFoodBottomSheetUiState = AddFoodBottomSheetUiState(),
    val onClickFood: (FoodUiState) -> Unit = { },
    val selectedFood: FoodUiState = FoodUiState()
)

data class AddFoodBottomSheetUiState(
    val foodUiState: FoodUiState = FoodUiState(),
    val quantity: String = "1"
)

data class FoodUiState(
    val id: Int = 0,
    val name: String = "",
    val measure: String = "",
    val calories: String = "0",
    val carbohydrates: String = "0",
    val proteins: String = "0",
    val fats: String = "0"
)