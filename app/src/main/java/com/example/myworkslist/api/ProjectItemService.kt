package com.example.myworkslist.api

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://b1bd-118-175-11-7.ngrok.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProjectItemService {
    @GET("projects_api")
    suspend fun getProjects(): List<ProjectItem>?
}

object ProjectItemApi {
    val retrofitService: ProjectItemService by lazy {
        retrofit.create(ProjectItemService::class.java)
    }
}