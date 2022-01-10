package com.example.myworkslist

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.home.ApiStatus
import com.example.myworkslist.home.HomeViewModel

@BindingAdapter("connectionAnimation")
fun bindConnectionAnimation(animationImage: ImageView, status: ApiStatus?) {
    status?.let {
        when (status) {
            ApiStatus.LOADING -> {
                animationImage.visibility = View.VISIBLE
                animationImage.setImageResource(R.drawable.loading_animation)
            }
            ApiStatus.ERROR -> {
                animationImage.visibility = View.VISIBLE
                animationImage.setImageResource(R.drawable.ic_connection_error)
            }
            ApiStatus.DONE -> {
                animationImage.visibility = View.GONE
            }
        }
    }
}

@BindingAdapter("responsibleStatus", "viewModel")
fun bindResponsibleStatus(view: View, projectItem: ProjectItem?, viewModel: HomeViewModel?) {
    if (projectItem != null && viewModel != null) {
        if (projectItem.projectName == viewModel.employeeName.value) {
            view.setBackgroundColor(Color.CYAN)
        }
    }
}