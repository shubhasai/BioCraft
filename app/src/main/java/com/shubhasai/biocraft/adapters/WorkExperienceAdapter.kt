package com.shubhasai.biocraft.adapters

import android.content.Context
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhasai.biocraft.R
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.workexperience

class WorkExperienceAdapter(private val context: Context?, val work: ArrayList<workexperience>, val listener: WorkExperienceAdapter.WorkDelClicked,val choice:Int): RecyclerView.Adapter<WorkExperienceAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val companyName: TextView = itemView.findViewById(R.id.tvCompanyName)
        val position: TextView = itemView.findViewById(R.id.tvPosition)
        val work: TextView = itemView.findViewById(R.id.tvwork)
        val companytimeline: TextView = itemView.findViewById(R.id.tvjobDuration)
        val companyviewmore: ImageView = itemView.findViewById(R.id.btn_deletework)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder:ViewHolder
        when(choice){
            1->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.cnworkexperience, parent, false)
                )
            }
            2->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.dgworkexperience, parent, false)
                )
            }
            3->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.workexperience_singleitem, parent, false)
                )
            }
            4->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.workexperience_singleitem, parent, false)
                )
            }
            5->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.workexperience_singleitem, parent, false)
                )
            }
            else->{
                viewholder = WorkExperienceAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.workexperience_singleitem, parent, false)
                )
            }
        }
        viewholder.companyviewmore.setOnClickListener {
            listener.oneworkDelclicked(work[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return work.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = work[position]
        holder.companyName.text = ed.nameOfTheOrganisation
        holder.companytimeline.text = ed.startMonth + " " + ed.startYear + "-"+ed.endMonth + " " + ed.endYear
        holder.position.text = ed.position
        val spannedText: Spanned = Html.fromHtml(ed.work)
        holder.work.text = spannedText
    }
    interface WorkDelClicked {
        fun oneworkDelclicked(work: workexperience){

        }
    }
}