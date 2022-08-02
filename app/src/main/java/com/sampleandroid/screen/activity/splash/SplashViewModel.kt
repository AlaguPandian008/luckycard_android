package com.sampleandroid.screen.activity.splash

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.animation.doOnEnd
import com.sampleandroid.R
import com.sampleandroid.adapter.CardsAdapter
import com.sampleandroid.adapter.base.BaseClickListener
import com.sampleandroid.model.data.CardData
import com.sampleandroid.screen.base.BaseViewModel
import com.sampleandroid.state.SplashViewState
import java.text.FieldPosition

class SplashViewModel : BaseViewModel<SplashViewState>() {

    private var list: MutableList<CardData>? = null

    private var count=0
    private var cardsAdapter: CardsAdapter? = null
    private var state: SplashViewState = SplashViewState.Init
        set(value) {
            field = value
            publishState(state)
        }

    private var cardOnePosition=-1
    private var cardTwoPosition=-1

    override fun onInitialized(bundle: Bundle?) {
        list= ArrayList()
        randomiseData()

    }
    private fun randomiseData()
    {
        list = mutableListOf(CardData("-1"),CardData("-1"),CardData("2"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"),CardData("-1"))
        var rnds =-1
        for (index in list?.indices!!) {
            if(index%2==0)
            {
                rnds = (0..1000).random()
            }
            list!![index].number=rnds.toString()

        }
        list?.shuffle()
        cardsAdapter = CardsAdapter(list!!,clickListener )
        state = SplashViewState.UpdateCardAdapter(cardsAdapter)

    }


    private fun flipAll()
    {
        for (index in list?.indices!!) {
            if(list!![index].isFlipped&&list!![index].isCorrect.not())
            {
                list!![index].isFlipped=false
                cardOnePosition=-1
                cardTwoPosition=-1
                state =   SplashViewState.FlipCardView(codeSnippet.getContext(),list!![index].view2!!,list!![index].view1!!)

            }

        }
    }
    fun cardFunction(position: Int)
    {
        if(cardOnePosition==-1)
        {
            cardOnePosition=position
            cardTwoPosition=-1
            state =   SplashViewState.UpdateCount(++count)

        }else if(cardOnePosition!=-1&&cardTwoPosition==-1&&cardOnePosition!=position)
        {
            cardTwoPosition=position
            if(list!![cardOnePosition].number==list!![cardTwoPosition].number)
            {
                list!![cardOnePosition].isCorrect=true
                list!![cardTwoPosition].isCorrect=true
                cardOnePosition=-1
                cardTwoPosition=-1
            }

            Handler(Looper.getMainLooper()).postDelayed({
                flipAll()

            }, 2000)
            state =   SplashViewState.UpdateCount(++count)

        }else{
            cardOnePosition=position
            cardTwoPosition=-1
        }

    }
    fun onClickRestart()
    {
        for (index in list?.indices!!) {
            if(list!![index].isFlipped&&list!![index].isCorrect)
            {
                list!![index].isFlipped=false
                list!![index].isCorrect=false
                cardOnePosition=-1
                cardTwoPosition=-1
                state =   SplashViewState.FlipCardView(codeSnippet.getContext(),list!![index].view2!!,list!![index].view1!!)

            }

        }
        randomiseData()
        count=0
        state =   SplashViewState.UpdateCount(0)


    }
    var clickListener=  object: BaseClickListener<CardData> {
        override fun onClickItem(data: CardData, position: Int) {


        }

        override fun flipCard(view1: View, view2: View, data: CardData, position: Int) {


            if((cardOnePosition==-1||cardTwoPosition==-1))
            {
                if(data.isFlipped)
                {
                    state =  SplashViewState.FlipCardView(codeSnippet.getContext(),view2,view1)

                }else{

                    state =    SplashViewState.FlipCardView(codeSnippet.getContext(),view1,view2)
                    data.view1=view1
                    data.view2=view2
                }

                cardFunction(position)

                data.isFlipped=!data.isFlipped
            }


        }

    }
}