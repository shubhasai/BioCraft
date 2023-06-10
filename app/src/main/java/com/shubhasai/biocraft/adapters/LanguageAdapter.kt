package com.shubhasai.biocraft.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhasai.biocraft.R
import com.shubhasai.biocraft.models.Education
import com.shubhasai.biocraft.models.language

class LanguageAdapter(private val context: Context?, val lang: ArrayList<language>, val listener: LanguageAdapter.LangDelClicked, val choice:Int): RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val langname: TextView = itemView.findViewById(R.id.tvLangName)
        val dellang: ImageView = itemView.findViewById(R.id.btn_deletelang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder:ViewHolder
        when(choice){
            1->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
            2->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
            3->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
            4->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
            5->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
            else->{
                viewholder = LanguageAdapter.ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.language_singleitem, parent, false)
                )
            }
        }

        viewholder.dellang.setOnClickListener {
            listener.onlangDelclicked(lang[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return lang.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ed = lang[position]
        holder.langname.text = ed.nameOfTheLanguage
    }
    interface LangDelClicked {
        fun onlangDelclicked(lang: language){

        }
    }
}