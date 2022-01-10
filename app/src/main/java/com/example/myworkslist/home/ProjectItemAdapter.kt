package com.example.myworkslist.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myworkslist.api.ProjectItem
import com.example.myworkslist.databinding.ProjectItemBinding

class ProjectItemAdapter : ListAdapter<ProjectItem, ProjectItemAdapter.ProjectItemPropertiesViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectItemAdapter.ProjectItemPropertiesViewHolder {
        return ProjectItemPropertiesViewHolder(ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: ProjectItemAdapter.ProjectItemPropertiesViewHolder,
        position: Int
    ) {
        val projectItem = getItem(position)
        holder.bind(projectItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProjectItem>() {
        override fun areItemsTheSame(oldItem: ProjectItem, newItem: ProjectItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProjectItem, newItem: ProjectItem): Boolean {
            return oldItem.projectName == newItem.projectName
        }
    }

    class ProjectItemPropertiesViewHolder(private val binding: ProjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(projectItem: ProjectItem) {
            binding.projectName.text = projectItem.projectName
            binding.province.text = projectItem.province
            binding.projectType.text = projectItem.projectType
            binding.mainResponsible.text = projectItem.responsibleName
            binding.assistant.text = projectItem.assistantName
            binding.executePendingBindings()
        }
    }
}