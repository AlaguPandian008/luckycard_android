package com.sampleandroid.di.module

import com.sampleandroid.utils.CodeSnippet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * Contains all the required dependencies
 * */

@ExperimentalCoroutinesApi
val helperModule = module {
    single { CodeSnippet(androidContext()) }
}


