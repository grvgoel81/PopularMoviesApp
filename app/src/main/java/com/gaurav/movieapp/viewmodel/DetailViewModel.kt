package com.gaurav.bookmyshowassignment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class DetailViewModel(private val movieId: Int, private var context: Context) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return DetailActivityViewModel(movieId, context) as T
    }

}