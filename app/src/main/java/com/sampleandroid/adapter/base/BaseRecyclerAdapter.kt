package com.sampleandroid.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V : BaseViewHolder<T, *>>(var data: MutableList<T>?) :
    RecyclerView.Adapter<V>() {

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.size = itemCount
        holder.wholeList = data
        holder.lastItemPosition = itemCount - 1
        holder.data = getItem(position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getItem(position: Int): T? {
        return data?.get(position)
    }



    fun inflateDataBinding(layout: Int, parent: ViewGroup): ViewDataBinding? {
        return DataBindingUtil.bind<ViewDataBinding>(
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        )
    }
}