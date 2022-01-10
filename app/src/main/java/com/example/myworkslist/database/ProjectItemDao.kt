package com.example.myworkslist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myworkslist.api.ProjectItem

@Dao
interface ProjectItemDao {
    @Query("select * from projectitem")
    fun getProjects(): LiveData<List<ProjectItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(projects: List<ProjectItem>)
}