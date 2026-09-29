package com.example.studentmanager.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.studentmanager.StudentManagerApp
import com.example.studentmanager.ui.screen.form.StudentFormViewModel
import com.example.studentmanager.ui.screen.list.StudentListViewModel

/** Factory untuk membuat semua ViewModel beserta dependensinya. */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            StudentListViewModel(studentManagerApp().repository)
        }
        initializer {
            StudentFormViewModel(createSavedStateHandle(), studentManagerApp().repository)
        }
    }
}

private fun CreationExtras.studentManagerApp(): StudentManagerApp =
    this[APPLICATION_KEY] as StudentManagerApp
