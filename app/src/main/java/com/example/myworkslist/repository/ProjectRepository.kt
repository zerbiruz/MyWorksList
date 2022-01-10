package com.example.myworkslist.repository

import androidx.lifecycle.LiveData
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.api.ProjectItemApi
import com.example.myworkslist.database.ProjectItemDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository(private val database: ProjectItemDatabase) {
    val projects: LiveData<List<ProjectItem>> = database.projectItemDao.getProjects()

    suspend fun refreshProject() {
        withContext(Dispatchers.IO) {
            val projectItemList = ProjectItemApi.retrofitService.getProjects()
            if (projectItemList != null) {
                database.projectItemDao.insertAll(projectItemList)
            }
        }
    }
}