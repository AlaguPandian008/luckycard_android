package com.sampleandroid.screen.activity.splash

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.sampleandroid.R
import com.sampleandroid.databinding.ActivitySplashBinding
import com.sampleandroid.screen.base.BaseActivity
import com.sampleandroid.state.SplashViewState


@SuppressLint("CustomSplashScreen")

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override val mViewModel: SplashViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_splash

    override fun onInitialize() {
        setTheme(R.style.splashScreen)
        mViewBinding.mViewModel=mViewModel
        mViewBinding.count="0"

    }


    override suspend fun subscribeObservers() {
        mViewModel.stateObserver.observe(this){

            when(it){
                is SplashViewState.UpdateCardAdapter-> mViewBinding.rvCards.adapter = it.adapter
                is SplashViewState.FlipCardView->flipCardView(it.context,it.visibleView,it.inVisibleView)
                is SplashViewState.UpdateCount->updateCountView(it.count)

                else -> {}
            }
            // navigateTo(it.action)

        }
    }
    private fun updateCountView( count:Int)
    {
        mViewBinding.count=count.toString()

    }



    private fun flipCardView(context: Context, visibleView: View, inVisibleView: View) {
        try {
            visibleView.visibility= View.VISIBLE
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()


        } catch (e: Exception) {
        }
    }

}