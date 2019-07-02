package com.gaurav.movieapp.model

import java.io.Serializable

data class MoviesResult(var voteCount: Int, var id: Int, val original_title: String, val release_date: String,
                        val poster_path: String, val title: String, val overview: String, val backdrop_path: String) : Serializable

data class Movies(var page: Int, var total_results: Int, var total_pages: Int, val results: List<MoviesResult>) :
    Serializable

data class ReviewResult(val author: String, val content: String, val id: String,
                        val url: String) : Serializable

data class Reviews(var id: Int, var page: Int, var total_pages: Int, val results: List<ReviewResult>) : Serializable

data class TrailerResult(var id: String, var iso_639_1: String, val iso_3166_1: String, val key: String,
                        val name: String, val site: String, val size: String, val type: String) : Serializable

data class Trailers(var id: Int, val results: List<TrailerResult>) : Serializable

data class SimilarMovieResult(var id: Int, val original_title: String, val release_date: String,
                              val poster_path: String, val title: String) : Serializable

data class SimilarMovies(var page: Int, var total_results: Int, var total_pages: Int, val results: List<SimilarMovieResult>) : Serializable

data class CreditResult(var cast_id: Int, val character: String, val credit_id: String, val name: String, val profile_path: String) : Serializable

data class Credits(var id: Int, val cast: List<CreditResult>) : Serializable