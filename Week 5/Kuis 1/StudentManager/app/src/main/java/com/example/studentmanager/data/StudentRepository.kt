package com.example.studentmanager.data

import kotlinx.coroutines.flow.Flow

/** Satu pintu akses data mahasiswa untuk ViewModel. */
class StudentRepository(private val dao: StudentDao) {

    val totalCount: Flow<Int> = dao.count()

    fun search(keyword: String): Flow<List<Student>> = dao.search(keyword.trim())

    suspend fun getById(id: Int): Student? = dao.getById(id)

    suspend fun isNimUsed(nim: String, excludeId: Int = 0): Boolean =
        dao.countByNim(nim, excludeId) > 0

    suspend fun insert(student: Student) = dao.insert(student)

    suspend fun update(student: Student) = dao.update(student)

    suspend fun delete(student: Student) = dao.delete(student)
}
