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
import com.shubhasai.biocraft.models.extracurricular
import com.shubhasai.biocraft.models.language

class ExtracurricularAdapter(private val context: Context?, val activities: ArrayList<extracurricular>, val listener: ExtracurricularAdapter.ActivityDelClicked, val choice:Int): RecyclerView.Adapter<ExtracurricularAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val activityname: TextView = itemView.findViewById(R.id.tvActivityName)
        val activitydes: TextView = itemView.findViewById(R.id.tvActivityDescription)
        val delactivity: ImageView = itemView.findViewById(R.id.btn_deleteactivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewholder: ViewHolder
        when(choice){
            1->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.cnactivities, parent, false)
                )
            }
            2->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.dgactivities, parent, false)
                )
            }
            3->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.extraactivities_singleitem, parent, false)
                )
            }
            4->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.extraactivities_singleitem, parent, false)
                )
            }
            5->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.extraactivities_singleitem, parent, false)
                )
            }
            else->{
                viewholder = ExtracurricularAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.extraactivities_singleitem, parent, false)
                )
            }

        }
        viewholder.delactivity.setOnClickListener {
            listener.onactivityDelclicked(activities[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = activities[position]
        holder.activityname.text = ed.nameOfTheActivity
        holder.activitydes.text = ed.description
    }
    interface ActivityDelClicked {
        fun onactivityDelclicked(act: extracurricular){

        }
    }
}