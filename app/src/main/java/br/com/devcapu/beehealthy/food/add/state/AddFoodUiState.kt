package br.com.devcapu.beehealthy.food.add.state

data class AddFoodUiState(
    val searchedFood: String = "",
    val onSearchedFood: (String) -> Unit = { },
    val foodListUiState: List<FoodUiState> = emptyList(),
    val onClickFood: (FoodUiState) -> Unit = { }
)

data class FoodUiState(
    val name: String = "",
    val measure: String = "",
    val calories: String = "",
    val carbohydrates: String = "",
    val proteins: String = "",
    val fats: String = ""
)