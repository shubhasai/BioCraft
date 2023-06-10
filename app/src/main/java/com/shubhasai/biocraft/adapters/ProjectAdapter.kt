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
import com.shubhasai.biocraft.models.projects

class ProjectAdapter(private val context: Context?, val project: ArrayList<projects>, val listener: ProjectAdapter.ProjectDelClicked,val choice:Int): RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val projectname: TextView = itemView.findViewById(R.id.tvNameofProject)
        val projectdes: TextView = itemView.findViewById(R.id.tvProjectDes)
        val projectlink: TextView = itemView.findViewById(R.id.tvProjectLink)
        val learned: TextView = itemView.findViewById(R.id.tvLearning)
        val delacheivement: ImageView = itemView.findViewById(R.id.btn_deleteProject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder :ViewHolder
        when (choice){
            1->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.cnproject, parent, false)
                )
            }
            2->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.dgproject, parent, false)
                )
            }
            3->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.projects_singleitem, parent, false)
                )
            }
            4->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.projects_singleitem, parent, false)
                )
            }
            5->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.projects_singleitem, parent, false)
                )
            }
            else->{
                viewholder = ProjectAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.projects_singleitem, parent, false)
                )
            }
        }

        viewholder.delacheivement.setOnClickListener {
            listener.onprojectclicked(project[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return project.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = project[position]
        holder.projectname.text = ed.nameOfTheProject
        holder.projectlink.text = ed.link
        holder.projectdes.text = ed.description
        holder.learned.text = "Learned: "+ed.learned
    }
    interface ProjectDelClicked {
        fun onprojectclicked(project: projects){

        }
    }
}