package com.example.studentmanager.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StudentValidatorTest {

    @Test
    fun nim_kosong_ditolak() {
        assertEquals("NIM wajib diisi", StudentValidator.validateNim(""))
        assertEquals("NIM wajib diisi", StudentValidator.validateNim("   "))
    }

    @Test
    fun nim_selain_angka_ditolak() {
        assertEquals("NIM hanya boleh berisi angka", StudentValidator.validateNim("23A1001"))
        assertEquals("NIM hanya boleh berisi angka", StudentValidator.validateNim("2301 001"))
    }

    @Test
    fun nim_angka_diterima() {
        assertNull(StudentValidator.validateNim("2301001"))
    }

    @Test
    fun nama_kosong_ditolak() {
        assertEquals("Nama wajib diisi", StudentValidator.validateName(" "))
        assertNull(StudentValidator.validateName("Budi Santoso"))
    }

    @Test
    fun program_studi_harus_dari_daftar() {
        assertEquals("Program studi wajib dipilih", StudentValidator.validateStudyProgram(""))
        assertEquals("Program studi wajib dipilih", StudentValidator.validateStudyProgram("Kedokteran"))
        assertNull(StudentValidator.validateStudyProgram("Informatika"))
    }
}
