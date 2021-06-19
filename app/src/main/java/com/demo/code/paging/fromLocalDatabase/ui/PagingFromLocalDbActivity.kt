package com.demo.code.paging.fromLocalDatabase.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.R
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingFromLocalDbBinding

class PagingFromLocalDbActivity : BaseActivity() {

    private lateinit var binding: ActivityPagingFromLocalDbBinding

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }

    private val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingFromLocalDbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.movieRecyclerView.adapter = adapter

        viewModel.allMovies.observe(this, Observer(adapter::submitList))

        setupSwipeToDelete()
    }


    private fun setupSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /*(viewHolder as? MovieListAdapter.MovieViewHolder)?.movie?.let {
                    viewModel.remove(it)
                }*/
            }
        }).attachToRecyclerView(binding.movieRecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goto_top -> scrollToTop()
            R.id.goto_bottom -> scrollToBottom()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun scrollToTop() {
        binding.movieRecyclerView.scrollToPosition(0)
    }

    private fun scrollToBottom() {
        val position = binding.movieRecyclerView.adapter?.itemCount
        position?.let {
            binding.movieRecyclerView.scrollToPosition(it - 1)
        }

    }

}