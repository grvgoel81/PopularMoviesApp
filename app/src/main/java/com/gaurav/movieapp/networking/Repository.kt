package com.gaurav.bookmyshowassignment.networking

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import com.gaurav.movieapp.model.*

class Respository {

    private var mData: LiveData<List<MoviesResult>>? = null
    private var movieID: Int = 0
    private var mReviewResult: LiveData<List<ReviewResult>>? = null
    private var mTrailerResult: LiveData<List<TrailerResult>>? = null
    private var mSimilarMoviesResult: LiveData<List<SimilarMovieResult>>? = null
    private var mCastResult: LiveData<List<CreditResult>>? = null

    constructor(application: Application) {
        ApiCall.fetchData("popular")
    }

    constructor(movie1ID: Int, context: Context) {
        this.movieID = movie1ID
        ApiCall.fetchMovieReview(movieID)
        ApiCall.fetchMovieTrailer(movieID)
        ApiCall.fetchSimilarMovies(movie1ID)
        ApiCall.fetchCastData(movie1ID)
    }

    fun mLiveData(): LiveData<List<MoviesResult>>? {
        mData = ApiCall.movieData
        return mData
    }

    fun mReviewLiveData(): LiveData<List<ReviewResult>>? {
        mReviewResult = ApiCall.reviewsData

        return mReviewResult
    }

    fun mTrailerLiveData(): LiveData<List<TrailerResult>>? {
        mTrailerResult = ApiCall.trailerData
        return mTrailerResult
    }

    fun mSimilarMoviesLiveData(): LiveData<List<SimilarMovieResult>>? {
        mSimilarMoviesResult = ApiCall.similarMoviesData
        return mSimilarMoviesResult
    }

    fun mCastLiveData(): LiveData<List<CreditResult>>? {
        mCastResult = ApiCall.castData
        return mCastResult
    }

}
