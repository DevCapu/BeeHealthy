package br.com.devcapu.beehealthy.food.add.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.food.add.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.components.AddFoodBottomSheet
import br.com.devcapu.beehealthy.food.add.components.AppBar
import br.com.devcapu.beehealthy.food.add.state.AddFoodUiState
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFoodScreen(
    viewModel: AddFoodViewModel = viewModel(),
    mainNavController: NavController,
) {
    val uiState by viewModel.state.collectAsState()
    val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
    val scope = rememberCoroutineScope()

    AddFoodScreen(
        content = {
            Scaffold(
                topBar = { AppBar { mainNavController.popBackStack() } }
            ) {
                FoodListWithSearch(uiState = uiState) {
                    viewModel.selectedFood(it)
                    scope.launch { sheetState.show() }
                }
            }
        },
        bottomSheet = {
            AddFoodBottomSheet(uiState = uiState.selectedFood) {
                viewModel.addFood(uiState.selectedFood)
                scope.launch {
                    sheetState.hide()
                }
            }
        },
        sheetState = sheetState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFoodScreen(
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
        content = { content() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AddFoodScreenWithouthBottomSheetPreview() {
    BeeHealthyTheme {
        AddFoodScreen(
            content = { FoodListWithSearch(uiState = AddFoodUiState()) { } },
            bottomSheet = { AddFoodBottomSheet(uiState = FoodUiState()) { } },
            sheetState = rememberModalBottomSheetState(initialValue = Hidden)
        )
    }
}