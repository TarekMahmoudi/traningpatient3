package com.example.traningpatient3.data.datasource

import com.example.traningpatient3.domain.model.add.AddPatientRemoteModel
import com.example.traningpatient3.domain.model.add.BodyAddPatientModel
import com.example.traningpatient3.domain.model.patients.PatientsWrappedRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrappedRemoteModel

    @POST("patients")
    suspend fun addPatients(@Body bodyAddPatientModel : BodyAddPatientModel): AddPatientRemoteModel
}