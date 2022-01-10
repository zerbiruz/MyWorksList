package com.example.myworkslist.home

import android.app.Application
import androidx.lifecycle.*
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.database.ProjectItemDatabase
import com.example.myworkslist.repository.ProjectRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException


enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var employeeName = MutableLiveData<String>("ธีระ")

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val projectsRepository = ProjectRepository(ProjectItemDatabase.getDatabase(application))

    var projectItemResults: LiveData<List<ProjectItem>> = projectsRepository.projects

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                projectsRepository.refreshProject()
                _status.value = ApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}