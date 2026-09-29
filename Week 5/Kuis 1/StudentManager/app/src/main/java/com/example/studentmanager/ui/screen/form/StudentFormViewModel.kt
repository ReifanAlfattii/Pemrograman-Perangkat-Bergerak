package com.example.studentmanager.ui.screen.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentmanager.data.Student
import com.example.studentmanager.data.StudentRepository
import com.example.studentmanager.data.StudentValidator
import com.example.studentmanager.ui.navigation.StudentDestinations
import kotlinx.coroutines.launch

data class StudentFormUiState(
    val nim: String = "",
    val name: String = "",
    val studyProgram: String = "",
    val nimError: String? = null,
    val nameError: String? = null,
    val studyProgramError: String? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isSaved: Boolean = false
)

/** Dipakai oleh form Tambah dan form Edit. Mode edit aktif jika ada argumen studentId. */
class StudentFormViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: StudentRepository
) : ViewModel() {

    private val studentId: Int? = savedStateHandle[StudentDestinations.STUDENT_ID_ARG]
    val isEditMode: Boolean = studentId != null

    var uiState by mutableStateOf(StudentFormUiState(isLoading = isEditMode))
        private set

    init {
        if (studentId != null) {
            viewModelScope.launch {
                val student = repository.getById(studentId)
                uiState = uiState.copy(
                    nim = student?.nim.orEmpty(),
                    name = student?.name.orEmpty(),
                    studyProgram = student?.studyProgram.orEmpty(),
                    isLoading = false
                )
            }
        }
    }

    fun onNimChange(value: String) {
        uiState = uiState.copy(nim = value, nimError = null)
    }

    fun onNameChange(value: String) {
        uiState = uiState.copy(name = value, nameError = null)
    }

    fun onStudyProgramChange(value: String) {
        uiState = uiState.copy(studyProgram = value, studyProgramError = null)
    }

    fun save() {
        if (uiState.isSaving) return

        val nim = uiState.nim.trim()
        val name = uiState.name.trim().replace(Regex("\\s+"), " ")
        val studyProgram = uiState.studyProgram

        val nimError = StudentValidator.validateNim(nim)
        val nameError = StudentValidator.validateName(name)
        val studyProgramError = StudentValidator.validateStudyProgram(studyProgram)
        if (nimError != null || nameError != null || studyProgramError != null) {
            uiState = uiState.copy(
                nimError = nimError,
                nameError = nameError,
                studyProgramError = studyProgramError
            )
            return
        }

        uiState = uiState.copy(isSaving = true)
        viewModelScope.launch {
            if (repository.isNimUsed(nim, excludeId = studentId ?: 0)) {
                uiState = uiState.copy(isSaving = false, nimError = "NIM sudah terdaftar")
                return@launch
            }

            val student = Student(
                id = studentId ?: 0,
                nim = nim,
                name = name,
                studyProgram = studyProgram
            )
            if (isEditMode) repository.update(student) else repository.insert(student)
            uiState = uiState.copy(isSaving = false, isSaved = true)
        }
    }
}
