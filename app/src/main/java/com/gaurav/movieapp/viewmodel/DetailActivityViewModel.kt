package com.gaurav.bookmyshowassignment.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.gaurav.bookmyshowassignment.networking.Respository
import com.gaurav.movieapp.model.CreditResult
import com.gaurav.movieapp.model.ReviewResult
import com.gaurav.movieapp.model.SimilarMovieResult
import com.gaurav.movieapp.model.TrailerResult

class DetailActivityViewModel(id: Int, context: Context) : ViewModel() {

    private lateinit var reviewData: LiveData<List<ReviewResult>>
    private lateinit var trailerData: LiveData<List<TrailerResult>>
    private lateinit var similarMoviesData: LiveData<List<SimilarMovieResult>>
    private lateinit var castData: LiveData<List<CreditResult>>

    private val mRespository: Respository =
        Respository(id, context)

    fun getReviews(): LiveData<List<ReviewResult>> {
            reviewData = mRespository.mReviewLiveData()!!
            return reviewData
        }

    fun getTrailers(): LiveData<List<TrailerResult>> {
            trailerData = mRespository.mTrailerLiveData()!!
            return trailerData
    }

    fun getSimilarMovies(): LiveData<List<SimilarMovieResult>> {
        similarMoviesData = mRespository.mSimilarMoviesLiveData()!!
        return similarMoviesData
    }

    fun getCast(): LiveData<List<CreditResult>> {
        castData = mRespository.mCastLiveData()!!
        return castData
    }

}
