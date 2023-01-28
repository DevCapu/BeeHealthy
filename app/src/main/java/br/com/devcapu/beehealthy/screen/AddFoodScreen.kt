package br.com.devcapu.beehealthy.food.add.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.devcapu.beehealthy.uistate.UiState
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.viewmodel.AddFoodViewModel
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
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
    val scope = rememberCoroutineScope()

    when (uiState) {
        is UiState.Loading -> {
            val progress by remember { mutableStateOf(0.1f) }
            val animatedProgress = animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            ).value

            CircularProgressIndicator(progress = animatedProgress)
        }
        is UiState.Success -> {
            val addFoodUiState = (uiState as UiState.Success).data
            AddFoodScreen(
                content = {
                    Scaffold(
                        topBar = { AppBar(addFoodUiState.meal) { mainNavController.popBackStack() } }
                    ) {
                        FoodListWithSearch(uiState = addFoodUiState) {
                            viewModel.selectedFood(it)
                            scope.launch { sheetState.show() }
                        }
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
        is Error -> {
            // Show error message
        }
    }
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