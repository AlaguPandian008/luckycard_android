package com.sampleandroid.model.data

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize

data class CardData(
    var  number: String?="1",
    var  isFlipped: Boolean=false,
    var isCorrect: Boolean=false,
    var view1:View?=null,
    var view2:View?=null,

)

