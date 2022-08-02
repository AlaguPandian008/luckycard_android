package com.sampleandroid.adapter.viewholder


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.View
import androidx.core.animation.doOnEnd
import com.sampleandroid.R
import com.sampleandroid.adapter.base.BaseClickListener
import com.sampleandroid.adapter.base.BaseViewHolder
import com.sampleandroid.databinding.InflateCardsItemBinding
import com.sampleandroid.model.data.CardData
import com.sampleandroid.screen.activity.splash.SplashViewModel


class CardsViewHolder(view:InflateCardsItemBinding,private val listener: BaseClickListener<CardData>):
    BaseViewHolder<CardData, InflateCardsItemBinding>(view) {
    override fun populateData(data: CardData) {
        viewBinding.data=data
        viewBinding.position=adapterPosition

        viewBinding.listener=listener


    }



}