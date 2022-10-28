package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Body)

    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = { changeBottomTab(navController, item) }
            )
        }
    }
}

private fun changeBottomTab(navController: NavController, item: BottomNavItem) {
    navController.navigate(item.screen_route) {

        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(rememberNavController())
}