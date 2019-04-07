package com.jintin.clerk.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jintin.clerk.R
import com.jintin.clerk.obj.ClerkLog
import kotlinx.android.synthetic.main.adapter_log.view.*

class LogListAdapter : RecyclerView.Adapter<LogViewHolder>() {
    private var list: MutableList<ClerkLog> = mutableListOf()

    fun setData(list: List<ClerkLog>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<ClerkLog>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_log, parent, false)
        return LogViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val channelView = itemView.channelView
    private val textView = itemView.textView

    fun bind(clerkLog: ClerkLog) {
        if (clerkLog.channel.isEmpty()) {
            channelView.visibility = View.GONE
        } else {
            channelView.text = clerkLog.channel
            channelView.visibility = View.VISIBLE
        }

        textView.text = clerkLog.log
    }
}