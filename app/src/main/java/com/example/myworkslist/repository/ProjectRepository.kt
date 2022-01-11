package com.example.myworkslist.repository

import androidx.lifecycle.LiveData
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.api.ProjectItemApi
import com.example.myworkslist.database.ProjectItemDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ProjectRepository(private val database: ProjectItemDatabase) {
//    var projects: LiveData<List<ProjectItem>> = database.projectItemDao.getProjects()

    suspend fun refreshProject() {
        withContext(Dispatchers.IO) {
            val projectItemList = ProjectItemApi.retrofitService.getProjects()
            if (projectItemList != null) {
                database.projectItemDao.insertAll(projectItemList)
            }
        }
    }

    fun getDataWithFilter(filter: String): LiveData<List<ProjectItem>> {
        return database.projectItemDao.getProjectWithFilter(filter)
    }

    fun getData(): LiveData<List<ProjectItem>> {
        return database.projectItemDao.getProjects()
    }
}