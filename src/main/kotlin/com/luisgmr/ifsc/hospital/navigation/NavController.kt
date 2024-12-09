package com.luisgmr.ifsc.hospital.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.luisgmr.ifsc.hospital.Screen

class NavController(
    private val startDestination: Screen,
    private var backStackScreens: MutableSet<Screen> = mutableSetOf()
) {
    var currentScreen: MutableState<Screen> = mutableStateOf(startDestination)

    var arguments: MutableMap<Screen, Map<String, Any>> = mutableMapOf()

    fun navigate(route: Screen, args: Map<String, Any>? = null) {
        if (route != currentScreen.value) {
            if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreens.remove(currentScreen.value)
            }

            if (route == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                backStackScreens.add(currentScreen.value)
            }

            if (args != null) {
                arguments[route] = args
            }

            currentScreen.value = route
        }
    }

    fun navigateBack() {
        if (backStackScreens.isNotEmpty()) {
            val lastScreen = backStackScreens.last()
            currentScreen.value = lastScreen
            backStackScreens.remove(lastScreen)
        }
    }

    fun getArgumentsForCurrentScreen(): Map<String, Any>? {
        return arguments[currentScreen.value]
    }
}

@Composable
fun rememberNavController(
    startDestination: Screen,
    backStackScreens: MutableSet<Screen> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}