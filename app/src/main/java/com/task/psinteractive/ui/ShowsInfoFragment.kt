package com.task.psinteractive.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.task.psinteractive.ShowsInfoViewModel
import com.task.psinteractive.ShowsListAdapter
import com.task.psinteractive.databinding.FragmentShowsInfoBinding

class ShowsInfoFragment: Fragment() {

    val viewModel: ShowsInfoViewModel by viewModels()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the ShowInfoFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowsInfoBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the ShowsInfoViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the ShowsListAdapter RecyclerView
        binding.showsList.adapter = ShowsListAdapter()

        return binding.root
    }
}