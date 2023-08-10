package com.example.traningpatient3.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traningpatient3.domain.model.patients.PatientRemoteModel
import com.example.traningpatient3.presentation.databinding.RowPatientBinding

class PatientsAdapter() :
    ListAdapter<PatientRemoteModel,PatientsAdapter.PatientViewHolder>(diffCallBack) {

    var lastSelected = -1




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
       val model = getItem(position)
        holder.bind(model, position)
    }




   inner class PatientViewHolder(private val  binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

       fun bind(model: PatientRemoteModel, position: Int) {
           binding.model = model


           binding.cardView.setOnClickListener {
               if (lastSelected != -1) {
                   getItem(position).selected = false
                   notifyItemChanged(lastSelected)
               }
               lastSelected = position
               getItem(position).selected = true

               notifyItemChanged(position)

           }


       }
   }
    private object diffCallBack : DiffUtil.ItemCallback<PatientRemoteModel>(){
        override fun areItemsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel
        ): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientRemoteModel,
            newItem: PatientRemoteModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    }

