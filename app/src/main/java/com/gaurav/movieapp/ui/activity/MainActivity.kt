package com.gaurav.movieapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.gaurav.bookmyshowassignment.ui.adapter.MovieAdapter
import com.gaurav.bookmyshowassignment.viewmodel.MainActivityViewModel
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.MoviesResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val mLayoutManager = GridLayoutManager(this@MainActivity, 1)

        moviesRecyclerView.layoutManager = mLayoutManager
        moviesRecyclerView.itemAnimator = DefaultItemAnimator()
        moviesRecyclerView.isNestedScrollingEnabled = false
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java!!)

        if (savedInstanceState == null) run {
            getMoviesData()
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                movieAdapter!!.filter.filter(editable)
            }
        })

    }

    private fun getMoviesData() {
        progressBar.visibility= View.VISIBLE
        mainActivityViewModel.mLiveData()?.observe(this,
            Observer<List<MoviesResult>> { results -> setupRecyclerView(results) })
    }

    private fun setupRecyclerView(results: List<MoviesResult>?) {

        if (results != null) {
            progressBar.visibility= View.GONE
            movieAdapter = MovieAdapter(results, this@MainActivity)
            moviesRecyclerView.adapter = movieAdapter
            movieAdapter?.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "List Null", Toast.LENGTH_SHORT).show()
        }
    }

}

