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
import com.shubhasai.biocraft.models.Education

class EducationAdapter(private val context: Context?, val edu: ArrayList<Education>, val listener: EducationAdapter.EdcDelClicked,val choice:Int): RecyclerView.Adapter<EducationAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val instituteName: TextView = itemView.findViewById(R.id.tvInstituteName)
        val degree: TextView = itemView.findViewById(R.id.tvDegree)
        val specialisation: TextView = itemView.findViewById(R.id.tvwork)
        val timeline:TextView = itemView.findViewById(R.id.tvDuration)
        val viewmore: ImageView = itemView.findViewById(R.id.btn_deleteedu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder: ViewHolder
        when(choice){
            1->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.cneducation_singleview, parent, false)
                )
            }
            2->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.dgeducation, parent, false)
                )
            }
            3->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.eduction_singleitem, parent, false)
                )
            }
            4->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.eduction_singleitem, parent, false)
                )
            }
            5->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.eduction_singleitem, parent, false)
                )
            }
            else->{
                viewholder = ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.eduction_singleitem, parent, false)
                )
            }
        }

        viewholder.viewmore.setOnClickListener {
            listener.oneduDelclicked(edu[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return edu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = edu[position]
        holder.instituteName.text = ed.nameOfTheInstitute
        holder.timeline.text = ed.startMonth + " " + ed.startYear + "-"+ed.endMonth + " " + ed.endYear
        holder.degree.text = ed.degree
        holder.specialisation.text = ed.specialisation
    }
    interface EdcDelClicked {
        fun oneduDelclicked(edu: Education){

        }
    }
}