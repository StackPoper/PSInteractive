package com.task.psinteractive.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.psinteractive.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sortCounter = 0

        // Gets the sort button view and sets its click listener to sort and refresh the displayed data
        findViewById<Button>(R.id.sortBtn)?.let {
            it.setOnClickListener {
                refreshLayout(sortAscending(sortCounter))
                sortCounter++
            }
        }

        // Getting reference of swipeRefreshLayout and sets its on refresh listener to refresh the displayed data
       findViewById<SwipeRefreshLayout>(R.id.swipeLayout)?.let {
           it.setOnRefreshListener {
               it.isRefreshing = false
               refreshLayout(null)
           }
       }
    }

    // Decides to sort the data in ascending or descending order
    private fun sortAscending(sortCounter: Int) =
        sortCounter % 2 == 0

    private fun refreshLayout(sortAsc: Boolean?) {
        val fragment = supportFragmentManager.findFragmentById(R.id.showInfoFragment)
        if (fragment is ShowsInfoFragment)
            fragment.viewModel.getShowsInfo(sortAsc)
    }
}