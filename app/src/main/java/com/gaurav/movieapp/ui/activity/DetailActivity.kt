package com.gaurav.movieapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gaurav.bookmyshowassignment.ui.adapter.CastAdapter
import com.gaurav.bookmyshowassignment.ui.adapter.MovieReviewAdapter
import com.gaurav.bookmyshowassignment.ui.adapter.MovieTrailerAdapter
import com.gaurav.bookmyshowassignment.ui.adapter.SimilarMoviesAdapter
import com.gaurav.bookmyshowassignment.viewmodel.DetailActivityViewModel
import com.gaurav.bookmyshowassignment.viewmodel.DetailViewModel
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var mResult: MoviesResult
    lateinit var detailActivityViewModel: DetailActivityViewModel
    private var mMovieTrailerAdapter: MovieTrailerAdapter? = null
    private var mMovieReviewAdapter: MovieReviewAdapter? = null
    private var mSimilarMoviesAdapter: SimilarMoviesAdapter? = null
    private var mCastAdapter: CastAdapter? = null
    val EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "animal_image_transition_name"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val trailerLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recycler_Trailers.layoutManager = trailerLayoutManager
        recycler_Trailers.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        recycler_Trailers.itemAnimator = DefaultItemAnimator()

        val reviewLayoutManager = LinearLayoutManager(applicationContext)
        recycler_review.layoutManager = reviewLayoutManager
        recycler_review.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        recycler_review.itemAnimator = DefaultItemAnimator()

        val similarLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recycler_similar_movies.layoutManager = similarLayoutManager
        recycler_similar_movies.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        recycler_similar_movies.itemAnimator = DefaultItemAnimator()

        val castLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerCredits.layoutManager = castLayoutManager
        recyclerCredits.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
        recyclerCredits.itemAnimator = DefaultItemAnimator()

        val movie = intent.getSerializableExtra("movie")
        mResult = movie as MoviesResult
        val name = intent.extras!!.getString(EXTRA_ANIMAL_IMAGE_TRANSITION_NAME)
        val rating = movie.voteCount
        collapsingToolbar.title = mResult.original_title
        val factory = DetailViewModel(mResult.id, applicationContext)


        detailActivityViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel::class.java)

        image_poster.transitionName = name
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
            .into(appBarImage)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + mResult.poster_path)
            .into(image_poster)

        tvTitle.text = movie.title
        plot.text = movie.overview
        release.text = movie.release_date


        detailActivityViewModel.getReviews().observe(this,
            Observer<List<ReviewResult>> { reviewResults ->
                mMovieReviewAdapter = reviewResults?.let { MovieReviewAdapter(it) }
                recycler_review.adapter = mMovieReviewAdapter
            })

        detailActivityViewModel.getTrailers().observe(this,
            Observer<List<TrailerResult>> { trailerResults ->
                mMovieTrailerAdapter = trailerResults?.let {
                    MovieTrailerAdapter(it, this)
                }
                recycler_Trailers.adapter = mMovieTrailerAdapter
            })

        detailActivityViewModel.getSimilarMovies().observe(this,
            Observer<List<SimilarMovieResult>> { similarMovieResults ->
                mSimilarMoviesAdapter = similarMovieResults?.let {
                    SimilarMoviesAdapter(it, this)
                }
                recycler_similar_movies.adapter = mSimilarMoviesAdapter
            })

        detailActivityViewModel.getCast().observe(this,
            Observer<List<CreditResult>> { castResults ->
                mCastAdapter = castResults?.let {
                    CastAdapter(it, this)
                }
                recyclerCredits.adapter = mCastAdapter
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}