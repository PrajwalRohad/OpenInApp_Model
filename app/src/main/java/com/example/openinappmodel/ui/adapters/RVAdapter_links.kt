package com.example.openinappmodel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openinappmodel.R
import com.example.openinappmodel.domain.entities.LinkEntity

class RVAdapter_links(
    var list : List<LinkEntity>
) : RecyclerView.Adapter<RVAdapter_links.LinksItemHolder>() {

    inner class LinksItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvLinkName : TextView = view.findViewById(R.id.tv_linkname)
        val tvLinkClicks : TextView = view.findViewById(R.id.tv_linkclicks)
        val tvLinkSite : TextView = view.findViewById(R.id.tv_linksite)

        fun bindLink(
            name : String,
            clicks : String,
            site : String
        ) {
            tvLinkName.text = name
            tvLinkClicks.text = clicks
            tvLinkSite.text = site
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksItemHolder {
        val rvitem = LayoutInflater.from(parent.context).inflate(R.layout.rv_links_item, parent, false)
        return LinksItemHolder(rvitem)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: LinksItemHolder, position: Int) {
        val name = list[position].app
        val clicks = list[position].totalClicks.toString()
        val site = list[position].webLink
        holder.bindLink(name, clicks, site)
    }
}