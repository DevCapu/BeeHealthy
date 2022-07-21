package br.com.devcapu.beehealthy.app.ui.component

import br.com.devcapu.beehealthy.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Body : BottomNavItem("Corpo", R.drawable.ic_body, "my_network")
}
