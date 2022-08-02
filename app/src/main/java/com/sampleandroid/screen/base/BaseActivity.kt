package com.sampleandroid.screen.base


import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.sampleandroid.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewDataBinding> : AppCompatActivity() {

    protected abstract val mViewModel: BaseViewModel<*>
    protected lateinit var mViewBinding: VB

    @get:LayoutRes
    abstract val layoutId: Int?

    abstract fun onInitialize()

    abstract suspend fun subscribeObservers()


    lateinit var codeSnippet: CodeSnippet


    private var mParentView: View? = null

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        mParentView = window.decorView.findViewById(android.R.id.content)
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeSnippet = CodeSnippet(applicationContext)
        this@BaseActivity.layoutId?.let { layoutId ->
            mViewBinding = DataBindingUtil.setContentView(this, layoutId)
        }



        lifecycleScope.launch(Dispatchers.Main) {
            async { subscribeObservers() }.await()
           mViewModel.onInitialized(intent.extras)
            onInitialize()
        }

    }






    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }







}
