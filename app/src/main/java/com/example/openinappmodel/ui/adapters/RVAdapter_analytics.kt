package com.example.openinappmodel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openinappmodel.R

class RVAdapter_analytics(
    var list : List<Pair<String, String>>
) : RecyclerView.Adapter<RVAdapter_analytics.AnalyticsItemHolder>() {

    inner class AnalyticsItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvValue : TextView = view.findViewById(R.id.rv_analytics_item_value)
        val tvCategory : TextView = view.findViewById(R.id.rv_analytics_item_category)

        fun bind(value : String, category: String) {
            tvValue.text = value
            tvCategory.text = category
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyticsItemHolder {
        val rvitem = LayoutInflater.from(parent.context).inflate(R.layout.rv_analytics_item, parent, false)
        return AnalyticsItemHolder(rvitem)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: AnalyticsItemHolder, position: Int) {
        val value = list[position].first
        val category = list[position].second
        holder.bind(value, category)
    }
}