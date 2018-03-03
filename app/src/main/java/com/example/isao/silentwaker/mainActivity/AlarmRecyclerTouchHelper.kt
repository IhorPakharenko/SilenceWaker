package com.example.isao.silentwaker.mainActivity

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by Isao on 02.03.2018.
 */

class AlarmRecyclerTouchHelper(dragDirs: Int, swipeDirs: Int,
                               private val onRemoveItem: (holder: AlarmsAdapter.AlarmVH) -> Unit) :
        ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {


    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?): Boolean {
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            getDefaultUIUtil().onSelected((viewHolder as AlarmsAdapter.AlarmVH).foreground)
        }
    }

    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?,
                                 viewHolder: RecyclerView.ViewHolder?,
                                 dX: Float, dY: Float, actionState: Int,
                                 isCurrentlyActive: Boolean) {
        getDefaultUIUtil().onDrawOver(c,
                recyclerView,
                (viewHolder as AlarmsAdapter.AlarmVH).foreground,
                dX,
                dY,
                actionState,
                isCurrentlyActive
        )
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        getDefaultUIUtil().clearView((viewHolder as AlarmsAdapter.AlarmVH).foreground)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?,
                             viewHolder: RecyclerView.ViewHolder?,
                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        getDefaultUIUtil().onDraw(c,
                recyclerView,
                (viewHolder as AlarmsAdapter.AlarmVH).foreground,
                dX,
                dY,
                actionState,
                isCurrentlyActive
        )
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        onRemoveItem(viewHolder as AlarmsAdapter.AlarmVH)
    }

}