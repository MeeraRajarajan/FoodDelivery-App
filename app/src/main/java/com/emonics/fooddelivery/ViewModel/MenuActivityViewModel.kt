package com.emonics.fooddelivery.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuActivityViewModel : ViewModel() {

//    var count = 0

    var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun updateCount(cnt:Int) : Int
    {
//        ++count
        //count.value = (count.value)?.plus(1)
         // if( cnt!= 10)
        count.value = cnt.plus(1)
        return count.value!!
    }
    fun removeCount(cnt:Int) : Int
    {
        if( cnt!= 0)
             count.value = cnt.minus(1)
        return count.value!!
    }



}