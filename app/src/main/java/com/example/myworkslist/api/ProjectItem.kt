package com.example.myworkslist.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class ProjectItem constructor(
    @Json(name = "assistant_name")
    val assistantName: String,

    val budget: Int,

    @Json(name = "engineering_office")
    val engineeringOffice: String?,

    @PrimaryKey
    val id: Int,

    @Json(name = "project_name")
    val projectName: String,

    @Json(name = "project_type")
    val projectType: String,

    val province: String,

    @Json(name = "responsible_name")
    val responsibleName: String,

    val year: String
)