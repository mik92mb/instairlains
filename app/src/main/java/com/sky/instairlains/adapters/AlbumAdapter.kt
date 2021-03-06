package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.Album
import kotlinx.android.synthetic.main.item_album.view.*

interface OnClickItem {
    fun onClick(id: Int, nameAlbum : String)
}

class AlbumAdapter(
    private val context: Context,
    private val listener: OnClickItem) : RecyclerView.Adapter<AlbumAdapter.ItemUserHolder>() {

    private val albumList : ArrayList<Album> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return ItemUserHolder(view,listener)
    }

    override fun getItemCount(): Int  = albumList.size

    override fun onBindViewHolder(holder: ItemUserHolder, position: Int) {
        holder.bind(albumList[position])
    }

    fun addAlbum(lst: List<Album>) {
        albumList.addAll(lst)
        notifyDataSetChanged()
    }

    fun clearAll(){
        albumList.clear()
        notifyDataSetChanged()
    }

    class ItemUserHolder(private val view: View,private val listener: OnClickItem) : RecyclerView.ViewHolder(view) {
        fun bind (album: Album) {
            view.titleAlbum.text = album.title
            view.buttonVisualizza.setOnClickListener{
                listener.onClick(album.id, album.title)
            }
        }
    }
}