package com.example.natureexplorer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



class NatureAdapter(
    private val context: Context,
    private val itemList: List<NatureItem>,

    private val onItemClick: (NatureItem) -> Unit
) : RecyclerView.Adapter<NatureAdapter.NatureViewHolder>() {


    inner class NatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView       = itemView.findViewById(R.id.cardView)
        val imageView: ImageView     = itemView.findViewById(R.id.cardImage)
        val titleView: TextView      = itemView.findViewById(R.id.cardTitle)
        val descriptionView: TextView = itemView.findViewById(R.id.cardDescription)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_nature_card, parent, false)
        return NatureViewHolder(view)
    }


    override fun onBindViewHolder(holder: NatureViewHolder, position: Int) {
        val item = itemList[position]

        holder.imageView.setImageResource(item.imageResId)
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description


        holder.cardView.setOnClickListener {
            onItemClick(item)
        }
    }


    override fun getItemCount(): Int = itemList.size
}