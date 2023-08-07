package com.example.traningpatient3.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traningpatient3.databinding.RowPatientBinding
import com.example.traningpatient3.domain.model.patients.PatientRemoteModel

class PatientsAdapter(private val patients: List<PatientRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientViewHolder>() {

    var lastSelected = -1




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val model = patients[position]
        holder.bind(model, position)
    }

    override fun getItemCount(): Int {
        return patients.size
    }


   inner class PatientViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PatientRemoteModel, position: Int) {
            binding.model = model

            binding.cardView.setOnClickListener {
                if (lastSelected != -1){
                    patients[lastSelected].selected = false
                    notifyItemChanged(lastSelected)
                }
                lastSelected = position
                patients[position].selected = true

                notifyItemChanged(position)

            }



    }
}

    }