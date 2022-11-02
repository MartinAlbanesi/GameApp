package com.practice.gameapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.practice.gameapp.R
import com.practice.gameapp.domain.models.GameModel
import com.squareup.picasso.Picasso

class GameAdapter(list: MutableLiveData<List<GameModel>>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var gameList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = gameList.value!!
        holder.id.text = list[position].id.toString()
        holder.title.text = list[position].title
        Picasso.get().load(list[position].thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return gameList.value!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var title: TextView
        var thumbnail: ImageView

        init {
            id = itemView.findViewById(R.id.tv_nombre)
            title = itemView.findViewById(R.id.tv_descripcion)
            thumbnail = itemView.findViewById(R.id.iv_imagen)
        }
    }
}
