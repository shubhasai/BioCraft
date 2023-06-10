package com.shubhasai.biocraft.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhasai.biocraft.R
import com.shubhasai.biocraft.models.achievements

class AcheivementAdapter(private val context: Context?, val acheivement: ArrayList<achievements>, val listener: AcheivementAdapter.AcheivementDelClicked,val choice:Int): RecyclerView.Adapter<AcheivementAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val acheivementName: TextView = itemView.findViewById(R.id.tvAcheivement)
        val delacheivement: ImageView = itemView.findViewById(R.id.btn_deleteacheivement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder: AcheivementAdapter.ViewHolder
        when(choice){
            1->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.cnacheivement, parent, false)
                )
            }
            2->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.dgacheivement, parent, false)
                )
            }
            3->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.achievements_singleitem, parent, false)
                )
            }
            4->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.achievements_singleitem, parent, false)
                )
            }
            5->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.achievements_singleitem, parent, false)
                )
            }
            else->{
                viewholder = AcheivementAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.achievements_singleitem, parent, false)
                )
            }
        }

        viewholder.delacheivement.setOnClickListener {
            listener.onacheivementDelclicked(acheivement[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return acheivement.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = acheivement[position]
        holder.acheivementName.text = ed.nameOfTheAcheivement
    }
    interface AcheivementDelClicked {
        fun onacheivementDelclicked(ach: achievements){

        }
    }
}