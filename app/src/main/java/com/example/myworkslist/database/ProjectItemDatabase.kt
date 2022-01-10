package com.example.myworkslist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myworkslist.api.ProjectItem

@Database(entities = [ProjectItem::class], version = 1)
abstract class ProjectItemDatabase : RoomDatabase() {
    abstract val projectItemDao: ProjectItemDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectItemDatabase? = null

        fun getDatabase(context: Context): ProjectItemDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ProjectItemDatabase::class.java, "projectitem").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}