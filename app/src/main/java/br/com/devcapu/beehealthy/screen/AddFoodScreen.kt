package br.com.devcapu.beehealthy.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.viewmodel.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.components.AddFoodBottomSheet
import br.com.devcapu.beehealthy.component.AppBar
import br.com.devcapu.beehealthy.component.FoodListWithSearch
import br.com.devcapu.beehealthy.uistate.AddFoodUiState
import br.com.devcapu.beehealthy.uistate.FoodUiState
import br.com.devcapu.beehealthy.uistate.UiState.Loading
import br.com.devcapu.beehealthy.uistate.UiState.Success
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AddFoodScreen(
    viewModel: AddFoodViewModel = viewModel(),
    mainNavController: NavController,
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
    val scope = rememberCoroutineScope()

    when (uiState) {
        is Loading -> { LoadingScreen() }
        is Success -> {
            val addFoodUiState = (uiState as Success).data
            AddFoodScreen(
                topBar = {
                    AppBar(
                        title = addFoodUiState.topBarTitle,
                        date = addFoodUiState.topBarDate,
                        onClickGoBack = { mainNavController.popBackStack() }
                    )
                },
                content = {
                    FoodListWithSearch(uiState = addFoodUiState) {
                        viewModel.selectedFood(FoodUiState())
                        scope.launch { sheetState.show() }
                    }
                },
                bottomSheet = {
                    AddFoodBottomSheet(uiState = addFoodUiState.selectedFood) {
                        viewModel.addFood(addFoodUiState.selectedFood)
                        scope.launch {
                            sheetState.hide()
                        }
                    }
                },
                sheetState = sheetState
            )
        }
        else -> { }
    }
}

@ExperimentalMaterialApi
@Composable
fun AddFoodScreen(
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    bottomSheet: @Composable () -> Unit,
    sheetState: ModalBottomSheetState,
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContent = { bottomSheet() },
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetContentColor = MaterialTheme.colors.onSurface,
        content = {
            Scaffold(
                topBar = { topBar() },
                content = { content() }
            )
        }
    )
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddFoodScreenWithouthBottomSheetPreview() {
    BeeHealthyTheme {
        AddFoodScreen(
            topBar = { AppBar("Almoço", date = "Sexta-feira 13") { } },
            content = { FoodListWithSearch(uiState = AddFoodUiState()) { } },
            bottomSheet = { AddFoodBottomSheet(uiState = FoodUiState()) { } },
            sheetState = rememberModalBottomSheetState(initialValue = Hidden)
        )
    }
}