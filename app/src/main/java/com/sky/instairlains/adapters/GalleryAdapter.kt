package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*

//interface OnItemClick {
//  fun onItemClick(stringUrl: String)
//}
class GalleryAdapter(
    private val context: Context
    //  private val listener: OnItemClick
) : RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder>() {

    private var photoList: ArrayList<Photo> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.PhotoViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount() = photoList.size

    fun addAll(list: List<Photo>) {
        photoList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        photoList = ArrayList()
        notifyDataSetChanged()
    }

    class PhotoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(photo: Photo) {
            val thumbnailUrl: String? = photo.thumbnailUrl
            Picasso.get()
                .load(thumbnailUrl)
                .into(view.photoAlbum)
        }
    }


}