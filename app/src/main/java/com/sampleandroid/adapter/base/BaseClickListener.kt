package com.sampleandroid.adapter.base

import android.view.View

interface BaseClickListener<T> {
    fun onClickItem(data: T, position: Int)
    fun flipCard(view1: View,view2: View,data: T,position: Int)

}