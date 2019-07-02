package com.gaurav.bookmyshowassignment.networking

import com.gaurav.movieapp.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/{filter}")
    fun getMovies(@Path("filter") filter: String, @Query("api_key") apiKey: String): Observable<Movies>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<Reviews>

    @GET("movie/{id}/videos")
    fun getMovieTrailers(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<Trailers>

    @GET("movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<SimilarMovies>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<Credits>

}