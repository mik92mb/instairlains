package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.Fly
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fly.view.*
import timber.log.Timber.i


class AirlainsAdapter(private val context: Context) : RecyclerView.Adapter<AirlainsAdapter.FlyHolder>() {

    private val flyList: ArrayList<Fly> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_fly, parent, false)
        return FlyHolder(view)
    }

    override fun getItemCount() = flyList.size

    override fun onBindViewHolder(holder: FlyHolder, position: Int) {
        holder.bind(flyList[position])
    }

    fun addAll(list: List<Fly>) {
        flyList.addAll(list)
        i(itemCount.toString())
        notifyDataSetChanged()
    }

    fun clear() {
        flyList.clear()
        notifyDataSetChanged()
    }

    class FlyHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(fly: Fly) {
            view.flight_number.text = fly.flight_number
            view.airlainName.text = fly.airline.name
            view.departure.text = fly.departure
            view.arrival.text = fly.arrival
            view.stops.text = fly.stops
            view.duration.text = fly.duration
            setImageLogo(view, fly)
        }

        private fun setImageLogo(view: View, fly: Fly) {
            Picasso.get()
                .load(fly.airline.logo)
                .into(view.airlainLogo)
        }
    }


}