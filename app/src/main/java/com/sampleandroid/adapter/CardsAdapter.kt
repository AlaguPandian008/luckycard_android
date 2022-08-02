package com.sampleandroid.adapter

import android.content.Context
import android.view.ViewGroup
import com.sampleandroid.R
import com.sampleandroid.adapter.base.BaseClickListener
import com.sampleandroid.adapter.base.BaseRecyclerAdapter
import com.sampleandroid.adapter.viewholder.CardsViewHolder
import com.sampleandroid.databinding.InflateCardsItemBinding
import com.sampleandroid.model.data.CardData
import com.sampleandroid.screen.activity.splash.SplashViewModel

class CardsAdapter ( var list:MutableList<CardData>, private val listener: BaseClickListener<CardData>):
    BaseRecyclerAdapter<CardData, CardsViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(

              inflateDataBinding(R.layout.inflate_cards_item, parent) as InflateCardsItemBinding,listener
        )
    }
}