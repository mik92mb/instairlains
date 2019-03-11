package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.User
import kotlinx.android.synthetic.main.item_user.view.*

interface OnItemClick {
    fun click(user: User, image: ImageView)
}

class UserAdapter(
    private val context: Context,
    private val listener: OnItemClick
) : RecyclerView.Adapter<UserAdapter.ItemUserHolder>() {

    private val usersList : ArrayList<User> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ItemUserHolder(view,listener)
    }

    override fun getItemCount(): Int  = usersList.size

    override fun onBindViewHolder(holder: ItemUserHolder, position: Int) {
        holder.bind(usersList[position])
    }

    fun addUsers(lst: List<User>) {
        usersList.addAll(lst)
        notifyDataSetChanged()
    }

    fun clearAll(){
        usersList.clear()
        notifyDataSetChanged()
    }

    class ItemUserHolder(private val view: View, private val listener: OnItemClick) : RecyclerView.ViewHolder(view) {
        fun bind (user: User) {
            view.nomeCognomeUser.text = user.toString()
            view.buttonProfilo.setOnClickListener{
                listener.click(user, view.circleImageView2)
            }
        }
    }
}