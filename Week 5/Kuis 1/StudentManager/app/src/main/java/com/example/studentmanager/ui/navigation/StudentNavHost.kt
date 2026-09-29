package com.example.studentmanager.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studentmanager.ui.screen.form.StudentFormScreen
import com.example.studentmanager.ui.screen.list.StudentListScreen
import kotlinx.coroutines.launch

object StudentDestinations {
    const val STUDENT_ID_ARG = "studentId"

    const val LIST_ROUTE = "students"
    const val ADD_ROUTE = "students/add"
    const val EDIT_ROUTE = "students/edit/{$STUDENT_ID_ARG}"

    fun editRoute(studentId: Int) = "students/edit/$studentId"
}

@Composable
fun StudentNavHost(navController: NavHostController = rememberNavController()) {
    // Snackbar dibuat di sini agar pesan dari form tetap tampil setelah kembali ke daftar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val navigateBack: () -> Unit = {
        // Cegah pop ganda saat tombol kembali ditekan cepat berkali-kali
        if (navController.previousBackStackEntry != null) navController.popBackStack()
    }
    val onFormSaved: (String) -> Unit = { message ->
        navigateBack()
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(message)
        }
    }

    NavHost(navController = navController, startDestination = StudentDestinations.LIST_ROUTE) {
        composable(StudentDestinations.LIST_ROUTE) {
            StudentListScreen(
                snackbarHostState = snackbarHostState,
                onAddClick = {
                    navController.navigate(StudentDestinations.ADD_ROUTE) { launchSingleTop = true }
                },
                onEditClick = { studentId ->
                    navController.navigate(StudentDestinations.editRoute(studentId)) { launchSingleTop = true }
                }
            )
        }

        composable(StudentDestinations.ADD_ROUTE) {
            StudentFormScreen(onNavigateBack = navigateBack, onSaved = onFormSaved)
        }

        composable(
            route = StudentDestinations.EDIT_ROUTE,
            arguments = listOf(navArgument(StudentDestinations.STUDENT_ID_ARG) { type = NavType.IntType })
        ) {
            StudentFormScreen(onNavigateBack = navigateBack, onSaved = onFormSaved)
        }
    }
}
