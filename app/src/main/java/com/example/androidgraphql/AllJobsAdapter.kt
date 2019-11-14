package com.example.androidgraphql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidgraphql.model.AllJobs

class AllJobsAdapter(private val allJobsList: List<AllJobs>) : RecyclerView.Adapter<AllJobsAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int = allJobsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.job_item, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val adapterItem = allJobsList[position]

        holder.jobTitle.text = adapterItem.jobTitle

        if (adapterItem.jobType.isNullOrEmpty()) {
            holder.jobType.visibility = View.GONE
        }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jobTitle: TextView = view.findViewById(R.id.jobTitle)
        val jobType: TextView = view.findViewById(R.id.jobType)
    }
}
