package com.example.isao.silentwaker.mainActivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.isao.silentwaker.R

/**
 * Created by Isao on 02.03.2018.
 */

class AlarmRecyclerTouchHelper(context: Context,
                               private val onRemoveItem: (holder: AlarmsAdapter.AlarmVH) -> Unit) :
        ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_24dp)
    private val intrinsicWidth = deleteIcon!!.intrinsicWidth
    private val intrinsicHeight = deleteIcon!!.intrinsicHeight
    private val background = ColorDrawable()

    init {
        background.color = ContextCompat.getColor(context, R.color.delete_color)
    }

    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?): Boolean {
        return true
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        // draw delete background
        background.setBounds(itemView.left,
                itemView.top, itemView.right, itemView.bottom)
        background.draw(c)

        // calculate position of delete icon
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val deleteIconBottom = deleteIconTop + intrinsicHeight
        val deleteIconLeft: Int
        val deleteIconRight: Int

        // if the swipe is from left to right
        if (dX > 0) {
            deleteIconLeft = itemView.left + deleteIconMargin
            deleteIconRight = itemView.left + deleteIconMargin + intrinsicWidth
        } else {
            deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            deleteIconRight = itemView.right - deleteIconMargin
        }

        // draw delete icon
        deleteIcon!!.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteIcon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        onRemoveItem(viewHolder as AlarmsAdapter.AlarmVH)
    }
}