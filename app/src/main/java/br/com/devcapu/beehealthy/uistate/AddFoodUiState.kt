package br.com.devcapu.beehealthy.uistate

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AddFoodUiState(
    val topBarTitle: String = "",
    val topBarDate: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE DD")).toString(),

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