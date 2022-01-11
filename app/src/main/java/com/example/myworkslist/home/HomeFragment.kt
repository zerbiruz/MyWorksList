package com.example.myworkslist.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myworkslist.R
import com.example.myworkslist.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var adapter: ProjectItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelFactory = HomeViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = ProjectItemAdapter()
        binding.projectList.adapter = adapter

        viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.project_type_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pr_menu -> {
                viewModel.updateDataWithFilter("pr")
                viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
            R.id.sr_menu -> {
                viewModel.updateDataWithFilter("sr")
                viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
            R.id.eimp_menu -> {
                viewModel.updateDataWithFilter("eimp")
                viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
            R.id.evaluation_menu -> {
                viewModel.updateDataWithFilter("งานประเมินผลโครงการ")
                viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
            R.id.all_project_menu -> {
                viewModel.getAllProjects()
                viewModel.projectItemResults.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
        }
        return true
    }
}