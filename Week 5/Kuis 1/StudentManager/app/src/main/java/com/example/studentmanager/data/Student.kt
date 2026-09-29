package com.example.studentmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Satu baris pada tabel `students`. NIM dibuat unik lewat index
 * sehingga database juga ikut menjaga agar tidak ada NIM ganda.
 */
@Entity(
    tableName = "students",
    indices = [Index(value = ["nim"], unique = true)]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nim: String,
    val name: String,
    @ColumnInfo(name = "study_program")
    val studyProgram: String
)

/** Pilihan program studi pada dropdown form. */
val STUDY_PROGRAMS = listOf(
    "Informatika",
    "Sistem Informasi",
    "Teknik Komputer",
    "Desain Komunikasi Visual",
    "Manajemen",
    "Akuntansi"
)
