package com.example.studentmanager

import android.app.Application
import com.example.studentmanager.data.StudentDatabase
import com.example.studentmanager.data.StudentRepository

/** Menyimpan satu instance repository untuk seluruh aplikasi. */
class StudentManagerApp : Application() {

    val repository: StudentRepository by lazy {
        StudentRepository(StudentDatabase.getInstance(this).studentDao())
    }
}
