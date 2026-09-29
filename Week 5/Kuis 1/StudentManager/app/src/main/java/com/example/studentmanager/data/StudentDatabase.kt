package com.example.studentmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_manager.db"
                )
                    .addCallback(SeedDataCallback)
                    .build()
                    .also { INSTANCE = it }
            }
    }

    /** Mengisi data contoh saat database dibuat pertama kali. */
    private object SeedDataCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL(
                """
                INSERT INTO students (nim, name, study_program) VALUES
                ('2301001', 'Budi Santoso', 'Informatika'),
                ('2301002', 'Siti Aminah', 'Sistem Informasi'),
                ('2301003', 'Andi Wijaya', 'Teknik Komputer')
                """
            )
        }
    }
}
