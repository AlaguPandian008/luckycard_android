package com.sampleandroid.state

import android.content.Context
import android.os.Bundle
import android.view.View
import com.sampleandroid.adapter.CardsAdapter


sealed class SplashViewState {
    object Init:SplashViewState()
    data class UpdateCardAdapter(var adapter: CardsAdapter?) :SplashViewState()
    data class FlipCardView(var context: Context, var visibleView: View, var inVisibleView: View) :SplashViewState()
    data class UpdateCount(var count: Int) :SplashViewState()


}
