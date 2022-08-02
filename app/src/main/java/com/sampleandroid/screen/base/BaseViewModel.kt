package com.sampleandroid.screen.base

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.sampleandroid.utils.CodeSnippet
import com.sampleandroid.utils.SingleLiveEvent
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.properties.Delegates

abstract class BaseViewModel<S> : ViewModel(), KoinComponent {


    var isNetworkAvailable by Delegates.notNull<Boolean>()


    protected val codeSnippet by inject<CodeSnippet>()

    abstract fun onInitialized(bundle: Bundle?)


    val baseLiveData = SingleLiveEvent<Pair<String, Any>>()

    val stateObserver: SingleLiveEvent<S> by lazy {
        SingleLiveEvent<S>()
    }

    protected fun publishState(state: S) {
        stateObserver.setValue(state)
    }




}