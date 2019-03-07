package com.sky.instairlains.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.data.network.model.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.ItemUserHolder>() {

    private val usersList : ArrayList<User> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ItemUserHolder(view)
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

    class ItemUserHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind (user: User) {
            view.nomeCognomeUser.text = user.toString()
        }
    }
}