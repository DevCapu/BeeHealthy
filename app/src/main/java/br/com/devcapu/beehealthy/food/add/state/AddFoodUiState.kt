package br.com.devcapu.beehealthy.food.add.state

data class AddFoodUiState(
    val searchedFood: String = "",
    val onSearchedFoodChange: (String) -> Unit = { },
    val foodListUiState: List<FoodUiState> = emptyList(),
    val addFoodBottomSheetUiState: AddFoodBottomSheetUiState = AddFoodBottomSheetUiState(),
    val onClickFood: (FoodUiState) -> Unit = { }
)

data class AddFoodBottomSheetUiState(
    val foodUiState: FoodUiState = FoodUiState(),
    val quantity: String = "1"
)

data class FoodUiState(
    val id: Int = 0,
    val name: String = "",
    val measure: String = "",
    val calories: String = "",
    val carbohydrates: String = "",
    val proteins: String = "",
    val fats: String = ""
)