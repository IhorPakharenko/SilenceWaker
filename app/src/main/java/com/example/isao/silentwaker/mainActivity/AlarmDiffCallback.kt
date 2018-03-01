package com.example.isao.silentwaker.mainActivity

import android.support.v7.util.DiffUtil
import com.example.isao.silentwaker.data.db.Alarm

/**
 * Created by Isao on 28.02.2018.
 */

class AlarmDiffCallback(private val old: List<Alarm>,
                        private val new: List<Alarm>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].id == new[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size
}