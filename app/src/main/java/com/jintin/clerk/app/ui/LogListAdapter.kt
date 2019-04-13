package com.jintin.clerk.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jintin.clerk.app.R
import com.jintin.clerk.app.obj.ClerkLog
import kotlinx.android.synthetic.main.adapter_log.view.*

/**
 * Adapter for viewing ClerkLog
 */
class LogListAdapter : RecyclerView.Adapter<LogViewHolder>() {
    private var list: MutableList<ClerkLog> = mutableListOf()

    /**
     * Set logs
     */
    fun setData(list: List<ClerkLog>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * Append logs
     */
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

/**
 * ViewHolder for LogListAdapter
 */
class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val channelView = itemView.channelView
    private val textView = itemView.textView

    /**
     * Bind ClerkLog with View
     */
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