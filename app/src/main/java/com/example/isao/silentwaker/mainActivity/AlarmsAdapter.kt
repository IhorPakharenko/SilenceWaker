package com.example.isao.silentwaker.mainActivity

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.isao.silentwaker.R
import com.example.isao.silentwaker.data.db.Alarm
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_alarm.*
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Isao on 23.02.2018.
 */

class AlarmsAdapter : RecyclerView.Adapter<AlarmsAdapter.AlarmVH>() {
    var alarms: List<Alarm> = ArrayList()
        set(value) {
            DiffUtil.calculateDiff(AlarmDiffCallback(alarms, value)).dispatchUpdatesTo(this)
            field = value
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

    class AlarmVH(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(alarm: Alarm) {
            tvAlarmTime.text = alarm.time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }
}