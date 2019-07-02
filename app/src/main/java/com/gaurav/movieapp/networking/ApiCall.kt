package com.gaurav.bookmyshowassignment.networking

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.gaurav.movieapp.model.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object ApiCall {

    private val data = MutableLiveData<List<MoviesResult>>()
    private val dataReviews = MutableLiveData<List<ReviewResult>>()
    private val dataTrailer = MutableLiveData<List<TrailerResult>>()
    private val dataSimilar = MutableLiveData<List<SimilarMovieResult>>()
    private val dataCast = MutableLiveData<List<CreditResult>>()

    private var mMoviesObservable: Observable<Movies>? = null
    private var mReviewsObservable: Observable<Reviews>? = null
    private var mTrailersObservable: Observable<Trailers>? = null
    private var mSimilarObservable: Observable<SimilarMovies>? = null
    private var mCastObservable: Observable<Credits>? = null

    private val compositeDisposable = CompositeDisposable()


    val movieData: LiveData<List<MoviesResult>>
        get() = data

    val reviewsData: LiveData<List<ReviewResult>>
        get() = dataReviews

    val trailerData: LiveData<List<TrailerResult>>
        get() = dataTrailer

    val similarMoviesData: LiveData<List<SimilarMovieResult>>
        get() = dataSimilar

    val castData: LiveData<List<CreditResult>>
        get() = dataCast


    fun fetchData(sort: String) {

        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        mMoviesObservable = apiService?.getMovies(sort, ApiClient.api_key)
        compositeDisposable.add(mMoviesObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Movies>() {
                override fun onNext(movies: Movies) {
                    val results = movies.results
                    data.postValue(results)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
        )
    }


    fun fetchMovieReview(id: Int) {

        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        mReviewsObservable = apiService?.getMovieReviews(id, ApiClient.api_key)
        compositeDisposable.add(mReviewsObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Reviews>() {
                override fun onNext(reviews: Reviews) {
                    val results = reviews.results
                    dataReviews.postValue(results)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
        )
    }

    fun fetchMovieTrailer(id: Int) {
        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        mTrailersObservable = apiService?.getMovieTrailers(id, ApiClient.api_key)
        mTrailersObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Trailers>() {
                override fun onNext(trailers: Trailers) {
                    val results = trailers.results
                    dataTrailer.postValue(results)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

    fun fetchSimilarMovies(id: Int) {
        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        mSimilarObservable = apiService?.getSimilarMovies(id, ApiClient.api_key)
        mSimilarObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<SimilarMovies>() {
                override fun onNext(similarMovies: SimilarMovies) {
                    val results = similarMovies.results
                    dataSimilar.postValue(results)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

    fun fetchCastData(id: Int) {
        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        mCastObservable = apiService?.getMovieCredits(id, ApiClient.api_key)
        mCastObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Credits>() {
                override fun onNext(credits: Credits) {
                    val results = credits.cast
                    dataCast.postValue(results)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

}