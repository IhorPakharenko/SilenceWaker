package com.example.isao.silentwaker.mainActivity

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.isao.silentwaker.R
import com.example.isao.silentwaker.data.db.Alarm

/**
 * Created by Isao on 23.02.2018.
 */

class AlarmsAdapter : RecyclerView.Adapter<AlarmsAdapter.AlarmVH>() {
    var alarms: List<Alarm> = ArrayList()

    fun updateList(new: List<Alarm>) {
        DiffUtil.calculateDiff(AlarmDiffCallback(alarms, new)).dispatchUpdatesTo(this)
        alarms = new
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmVH {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_alarm, parent, false)
        return AlarmVH(view)
    }

    override fun getItemCount() = alarms.size

    override fun onBindViewHolder(holder: AlarmVH, position: Int) {
        holder.bind(alarms[position])
    }



    class AlarmVH(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(alarm: Alarm) {
//            itemView.tvAlarmId.text = alarm.id.toString()
        }
    }
}