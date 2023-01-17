package br.com.devcapu.beehealthy.food.add.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.food.add.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.components.AddFoodBottomSheet
import br.com.devcapu.beehealthy.food.add.components.AppBar

@Composable
fun AddFoodScreen(
    viewModel: AddFoodViewModel = viewModel(),
    mainNavController: NavController
) {
    val uiState by viewModel.state.collectAsState()
    AddFoodScreen(
        uiState = uiState,
        mainNavController = mainNavController
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFoodScreen(
    uiState: AddFoodUiState,
    mainNavController: NavController
) {
    val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContent = { AddFoodBottomSheet() },
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetContentColor = MaterialTheme.colors.onSurface,
        content = {
            Scaffold(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                topBar = { AppBar { mainNavController.popBackStack() } },
            ) {
                FoodListWithSearch(uiState = uiState)
            }
        }
    )
}

@Preview(showSystemUi = true, )
@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AddFoodScreenPreview() {
    BeeHealthyTheme {
        AddFoodScreen(
            uiState = AddFoodUiState(),
            mainNavController = rememberNavController()
        )
    }
}