package com.eduardoomarson.todolistfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eduardoomarson.todolistfirebase.ui.feature.addedit.AddEditScreen
import com.eduardoomarson.todolistfirebase.ui.feature.list.ListScreen
import com.eduardoomarson.todolistfirebase.ui.feature.login.LoginScreen
import com.eduardoomarson.todolistfirebase.ui.feature.signup.SignupScreen
import com.eduardoomarson.todolistfirebase.authentication.AuthState
import com.eduardoomarson.todolistfirebase.authentication.AuthViewModel
import com.eduardoomarson.todolistfirebase.ui.feature.forgotpassword.ForgotPasswordScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute
@Serializable
object SignupRoute
@Serializable
object ForgotPasswordRoute
@Serializable
object ListRoute
@Serializable
data class AddEditRoute(val id: Long?= null)

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val authState = authViewModel.authState.observeAsState()

    val startDestination = if(authState.value is AuthState.Authenticated){
        ListRoute
    } else {
        LoginRoute
    }

    NavHost(navController = navController, startDestination = startDestination ) {

        composable<LoginRoute>{
            LoginScreen(
                navigateToListScreen = {
                    navController.navigate(ListRoute){
                        popUpTo(LoginRoute) { inclusive = true } // Sugestão CLaude
                    }
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignupRoute)
                },
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordRoute)
                },
                authViewModel = authViewModel
                )
        }

        composable<ForgotPasswordRoute> {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        composable<SignupRoute>{
            SignupScreen(
                navigateToListScreen = {
                    navController.navigate(ListRoute) {
                        popUpTo(SignupRoute) { inclusive = true }
                    }
                },
                navigateToLoginScreen = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        composable<ListRoute>{
            ListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                },
                navigateToLoginScreen = {
                    navController.navigate(LoginRoute) {
                        popUpTo(0) { inclusive = true } // Sugestão Claude
                    }
                },
                authViewModel = authViewModel
            )
        }

        composable<AddEditRoute>{ backStackEntry ->
            val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            AddEditScreen(
                id = addEditRoute.id,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}