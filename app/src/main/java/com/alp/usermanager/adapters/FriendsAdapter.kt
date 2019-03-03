package com.alp.usermanager.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alp.usermanager.R
import com.alp.usermanager.service.model.User
import kotlinx.android.synthetic.main.item_person.view.*

class FriendsAdapter (val friends : MutableList<User>) : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend = friends[position]
        holder.itemView.tv_name_surname.text = friend.getNameSurname()


    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FriendsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false)
        return FriendsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    class FriendsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}