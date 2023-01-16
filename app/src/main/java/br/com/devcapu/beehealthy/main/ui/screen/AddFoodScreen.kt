package br.com.devcapu.beehealthy.main.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Expanded
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphNavigator
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.food.add.components.AddFoodBottomSheet
import br.com.devcapu.beehealthy.food.add.components.AppBar
import br.com.devcapu.beehealthy.food.add.screen.FoodListWithSearch
import kotlinx.coroutines.launch

@Composable
fun AddFoodScreen(
    navigator: NavGraphNavigator,
) {
    AddFoodScreen(
        onClickGoBack = { navigator.popBackStack() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFoodScreen(
    onClickGoBack: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
    val scope = rememberCoroutineScope()
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
                topBar = { AppBar(onClickGoBack) },
            ) {
                FoodListWithSearch {
                    scope.launch {
                        sheetState.show()
                    }
                }
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
        AddFoodScreen { }
    }
}