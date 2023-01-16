package com.example.simpleremotetask.features.medicine.data.repository

import com.example.simpleremotetask.features.medicine.data.local.data_source.MedicineLocalDataSource
import com.example.simpleremotetask.features.medicine.data.mapper.toMedicine
import com.example.simpleremotetask.features.medicine.data.mapper.toMedicineModel
import com.example.simpleremotetask.features.medicine.data.remote.data_source.MedicineRemoteDataSource
import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import com.example.simpleremotetask.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MedicineRepositoryImpl(
    private val medicineRemoteDataSource: MedicineRemoteDataSource,
    private val medicineLocalDataSource: MedicineLocalDataSource
) : MedicineRepository {

    override suspend fun getMedications(): NetworkResult<List<Medicine>> =
        withContext(Dispatchers.IO) {
            try {
                val result = medicineRemoteDataSource.getMedicines()
                NetworkResult.Success(filterMedicines(result))
            } catch (e: Exception) {
                NetworkResult.Error(e.message)
            }
        }

    override suspend fun getMedicationById(id: Int): Medicine =
        medicineLocalDataSource.getMedicine(id).toMedicine()

    override suspend fun filterMedicines(result: DiseasesDto) =
        withContext(Dispatchers.IO) {
            val list: MutableList<Medicine> = mutableListOf()
            result.problems?.forEach {
                it?.medications?.forEach { medicine ->
                    launch {
                        cacheMedicines(it.id, medicine)
                    }
                    launch {
                        medicine?.toMedicine(it.id)?.run {
                            list.add(this)
                        }
                    }
                }
            }
            list
        }

    override suspend fun cacheMedicines(id: String?, medicine: Medication?) {
        medicine?.toMedicineModel(id)?.run {
            medicineLocalDataSource.insertMedicines(this)
        }
    }

}