package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

//interface OnItemClick {
  //  fun click(user: User, image: ImageView)
//}

class PostAdapter (
    private val context: Context//,
 //   private val listener: OnItemClick
) : RecyclerView.Adapter<PostAdapter.ItemPostHolder>() {

    private val postList : ArrayList<Post> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPostHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ItemPostHolder(view)
    }

    override fun getItemCount(): Int  = postList.size

    override fun onBindViewHolder(holder: ItemPostHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun addPosts(lst: List<Post>) {
        postList.addAll(lst)
        notifyDataSetChanged()
    }

    fun clearAll(){
        postList.clear()
        notifyDataSetChanged()
    }

    class ItemPostHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind (post: Post) {
            view.postTitle.text = post.title
            view.postBody.text = post.body
        }
    }
}