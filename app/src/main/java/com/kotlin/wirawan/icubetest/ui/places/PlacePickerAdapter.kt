package com.kotlin.wirawan.icubetest.ui.places

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.wirawan.icubetest.R
import com.kotlin.wirawan.icubetest.models.Place

class PlacePickerAdapter(private val context: Context, private val list: MutableList<Place>,
                  activity: Activity): RecyclerView.Adapter<PlacePickerAdapter.ListViewHolder>() {

    private val listener: PlacePickerAdapter.onItemClickListener

    init {
        this.listener = activity as PlacePickerAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var place = list[position]

        // holder!!.bind(post)
        holder.placeName.setText(place.name)
        holder.placeType.setText(place.type)
        holder.placePrice.setText(place.price.toString())

        holder.layout!!.setOnClickListener {
            listener.itemDetail(place.id.toString()!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return PlacePickerAdapter.ListViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val placeName = itemView.findViewById<TextView>(R.id.name_text_view)
        val placeType = itemView.findViewById<TextView>(R.id.type_text_view)
        val placePrice = itemView.findViewById<TextView>(R.id.price_text_view)
    }

    interface onItemClickListener {
        fun itemRemoveClick(place: Place)
        fun itemDetail(postId: String)
    }
}