package com.in22labs.tnskill.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V : RecyclerView.ViewHolder>(
    private val mList: ArrayList<T>) : RecyclerView.Adapter<V>() {

    var copyList: ArrayList<T> = arrayListOf()

    init {
        copyList.addAll(mList)
    }

    private var mListener: RecyclerviewItemClickListener = object : RecyclerviewItemClickListener {
        override fun onRecyclerviewItemClicked(position: Int, view: View, data: Any) {
        }
    }

    companion object {
        interface RecyclerviewItemClickListener {
            fun onRecyclerviewItemClicked(position: Int, view: View, data: Any)
        }
    }

    fun getAllDataSet(): ArrayList<T> {
        return mList
    }

    fun getAdapterDataSet(): ArrayList<T> {
        return copyList
    }

    fun setListener(mListener: RecyclerviewItemClickListener) {
        this.mListener = mListener;
    }

    fun getListener(): RecyclerviewItemClickListener {
        return mListener
    }

    open fun filterItems(noOfItem: Int) {
        if (mList.size > 0 && noOfItem > 0 && noOfItem < mList.size) {
            copyList.clear()
            copyList.addAll(getAllDataSet().subList(0, noOfItem))
        }
    }

    override fun getItemCount(): Int {
        return copyList.size
    }

}