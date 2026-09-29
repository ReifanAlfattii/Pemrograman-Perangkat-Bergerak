package com.example.studentmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    /**
     * Mencari mahasiswa berdasarkan nama, NIM, atau program studi.
     * Keyword kosong akan mengembalikan semua data.
     */
    @Query(
        """
        SELECT * FROM students
        WHERE name LIKE '%' || :keyword || '%'
           OR nim LIKE '%' || :keyword || '%'
           OR study_program LIKE '%' || :keyword || '%'
        ORDER BY nim ASC
        """
    )
    fun search(keyword: String): Flow<List<Student>>

    @Query("SELECT COUNT(*) FROM students")
    fun count(): Flow<Int>

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getById(id: Int): Student?

    /** Menghitung NIM yang sama, kecuali milik mahasiswa dengan [excludeId]. */
    @Query("SELECT COUNT(*) FROM students WHERE nim = :nim AND id != :excludeId")
    suspend fun countByNim(nim: String, excludeId: Int): Int

    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)
}
