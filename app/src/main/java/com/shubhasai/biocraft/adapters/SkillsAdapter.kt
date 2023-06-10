package com.shubhasai.biocraft.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhasai.biocraft.R
import com.shubhasai.biocraft.models.language
import com.shubhasai.biocraft.models.skills

class SkillsAdapter(private val context: Context?, val skills: ArrayList<skills>, val listener: SkillsAdapter.SkillDelClicked,val choice:Int): RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val skillname: TextView = itemView.findViewById(R.id.tvSkillname)
        val rating:RatingBar = itemView.findViewById(R.id.skillrating)
        val delskill: ImageView = itemView.findViewById(R.id.btn_deleteskill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder:ViewHolder
        when(choice){
            1->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
            2->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
            3->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
            4->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
            5->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
            else->{
                viewholder = SkillsAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.skills_singleitem, parent, false)
                )
            }
        }

        viewholder.delskill.setOnClickListener {
            listener.onskilldelClicked(skills[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = skills[position]
        holder.skillname.text = ed.nameOfTheSkill
        holder.rating.rating = ed.rating
    }
    interface SkillDelClicked {
        fun onskilldelClicked(skill: skills){

        }
    }
}