package com.gaurav.bookmyshowassignment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.gaurav.bookmyshowassignment.networking.Respository
import com.gaurav.movieapp.model.MoviesResult

class MainActivityViewModel : AndroidViewModel {

    private var mData: LiveData<List<MoviesResult>>? = null
    private var mDataFav: LiveData<List<MoviesResult>>? = null
    private val mRespository: Respository

    constructor(application: Application) : super(application){
        mRespository = Respository(application)
    }

    fun mLiveData(): LiveData<List<MoviesResult>>? {
        mData = mRespository.mLiveData()
        return mData
    }

}
