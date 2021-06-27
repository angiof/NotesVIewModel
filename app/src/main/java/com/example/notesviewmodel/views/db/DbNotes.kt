package com.example.notesviewmodel.views.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntitiyNote::class], version = 1, exportSchema = false)
abstract class DbNotes : RoomDatabase() {


    abstract fun notesDaos(): DaoNotes

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DbNotes? = null

        fun getDatabase(context: Context): DbNotes {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbNotes::class.java,
                    "notes"
                ).build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }

}