package com.example.studentmanager.data

/** Aturan validasi input form mahasiswa. Mengembalikan pesan error, atau null jika valid. */
object StudentValidator {

    fun validateNim(nim: String): String? = when {
        nim.isBlank() -> "NIM wajib diisi"
        !nim.all { it in '0'..'9' } -> "NIM hanya boleh berisi angka"
        else -> null
    }

    fun validateName(name: String): String? =
        if (name.isBlank()) "Nama wajib diisi" else null

    fun validateStudyProgram(studyProgram: String): String? =
        if (studyProgram !in STUDY_PROGRAMS) "Program studi wajib dipilih" else null
}
