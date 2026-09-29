package com.example.studentmanager.ui.screen.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentmanager.data.Student
import com.example.studentmanager.data.StudentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class StudentListUiState(
    val students: List<Student> = emptyList(),
    val totalCount: Int = 0,
    val isLoading: Boolean = true
)

class StudentListViewModel(private val repository: StudentRepository) : ViewModel() {

    /** Teks pada kolom pencarian. */
    var searchQuery by mutableStateOf("")
        private set

    /** Setiap kali [searchQuery] berubah, query ke Room dijalankan ulang. */
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<StudentListUiState> = combine(
        snapshotFlow { searchQuery }.flatMapLatest { repository.search(it) },
        repository.totalCount
    ) { students, totalCount ->
        StudentListUiState(students = students, totalCount = totalCount, isLoading = false)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = StudentListUiState()
    )

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

    fun clearSearch() {
        searchQuery = ""
    }

    fun delete(student: Student) {
        viewModelScope.launch { repository.delete(student) }
    }

    /** Mengembalikan data yang baru dihapus (aksi "Urungkan"). */
    fun restore(student: Student) {
        viewModelScope.launch {
            if (!repository.isNimUsed(student.nim)) repository.insert(student)
        }
    }
}
