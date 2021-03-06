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
class LogListAdapter : RecyclerView.Adapter<LogListAdapter.LogViewHolder>() {

    private var list: MutableList<ClerkLog> = mutableListOf()

    /**
     * Set logs
     */
    fun setData(list: List<ClerkLog>) {
        this.list.clear()
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

    /**
     * ViewHolder for LogListAdapter
     */
    class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Bind ClerkLog with View
         */
        fun bind(clerkLog: ClerkLog) {
            if (clerkLog.channel.isEmpty()) {
                itemView.channelView.visibility = View.GONE
            } else {
                itemView.channelView.text = clerkLog.channel
                itemView.channelView.visibility = View.VISIBLE
            }

            itemView.textView.text = clerkLog.log
        }

    }

}