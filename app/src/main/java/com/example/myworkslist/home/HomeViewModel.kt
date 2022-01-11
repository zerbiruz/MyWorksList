package com.example.myworkslist.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.database.ProjectItemDatabase
import com.example.myworkslist.repository.ProjectRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException


enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var employeeName = MutableLiveData<String>("ธีระ")

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val projectsRepository = ProjectRepository(ProjectItemDatabase.getDatabase(application))

//    private val _projectItemResults = MutableLiveData<List<ProjectItem>>()
//    val projectItemResults: LiveData<List<ProjectItem>>
//        get() = _projectItemResults
    lateinit var projectItemResults: LiveData<List<ProjectItem>>

    init {
        refreshDataFromRepository()
        viewModelScope.launch {
            projectItemResults = projectsRepository.getData()
//            projectItemResults = projectsRepository.getDataWithFilter("sr")
        }
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

    fun updateDataWithFilter(filter: String) {
        viewModelScope.launch {
            projectItemResults = projectsRepository.getDataWithFilter(filter)
        }
    }

    fun getAllProjects() {
        viewModelScope.launch {
            projectItemResults = projectsRepository.getData()
        }
    }
}