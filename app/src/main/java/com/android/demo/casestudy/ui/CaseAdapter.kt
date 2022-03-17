package com.android.demo.casestudy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.casestudy.databinding.AdapterMainBinding
import com.android.demo.casestudy.response.CaseStudies
import com.bumptech.glide.Glide

class CaseAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private var caseStudyList = mutableListOf<CaseStudies>()
    lateinit var mcontext: Context

    fun setCaseStudyList(caseStudyList: List<CaseStudies>) {
        this.caseStudyList.addAll(caseStudyList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        mcontext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val caseStudy = caseStudyList[position]
        holder.binding.textTeaser.text = caseStudy.teaser
        holder.binding.textTitle.text = caseStudy.title
        val heroImageUrl = caseStudy.heroImage
        Glide.with(holder.itemView.context).load(heroImageUrl).into(
            holder.binding.imageHero
        )
    }

    override fun getItemCount(): Int {
        return caseStudyList.size
    }
}

class MainViewHolder(val binding: AdapterMainBinding) :
    RecyclerView.ViewHolder(binding.root) {
}