package com.degel.android_interview.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.degel.android_interview.features.login.presentation.screen.LoginScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.degel.android_interview.features.medications.presentation.screen.DrugDetailsScreen
import com.degel.android_interview.features.medications.presentation.screen.HomeScreen
import com.degel.android_interview.features.medications.presentation.viewmodel.SelectedDrugViewModel

@Composable
fun MedicationAppNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = LoginScreenRoute) {
        composable<LoginScreenRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(
                        MedicationGraph,
                    ) {
                        popUpTo(LoginScreenRoute) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        navigation<MedicationGraph>(
            startDestination = MedicationScreenRoute
        ) {

            composable<MedicationScreenRoute> {
                val selectedDrugViewModel =
                    it.sharedKoinViewModel<SelectedDrugViewModel>(navController)
                LaunchedEffect(true) {
                    selectedDrugViewModel.onSelectedDrug(null)
                }
                HomeScreen(
                    onDrugClick = { selectedDrug ->
                        selectedDrugViewModel.onSelectedDrug(selectedDrug)
                        navController.navigate(
                            MedicationDetailScreenRoute
                        )
                    },
                    onLogout = {
                        navController.navigate(LoginScreenRoute) {
                            popUpTo(MedicationGraph) {
                            }
                        }
                    }
                )
            }
            composable<MedicationDetailScreenRoute> {
                val selectedDrugViewModel =
                    it.sharedKoinViewModel<SelectedDrugViewModel>(navController)
                val selectedDrug by selectedDrugViewModel.selectedDrug.collectAsStateWithLifecycle()

                DrugDetailsScreen(
                    drug = selectedDrug,
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


// here to use shared view model across screens
@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(
        viewModelStoreOwner = parentEntry
    )

}