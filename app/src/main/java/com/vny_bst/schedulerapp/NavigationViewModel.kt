package com.vny_bst.schedulerapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Vinay Singh Bisht on 13-Oct-21.
 */
class NavigationViewModel : ViewModel() {

    private val TAG = NavigationViewModel::class.java.simpleName

    private val clickItemPosition = MutableLiveData<Int>()

    fun setClickedPosition(position: Int) {
        clickItemPosition.postValue(position)
    }

    fun clickedPosition() = clickItemPosition

}