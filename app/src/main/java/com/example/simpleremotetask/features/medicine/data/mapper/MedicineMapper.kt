package com.example.simpleremotetask.features.medicine.data.mapper

import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.domain.model.Medicine

fun Medication.toMedicineModel(diseaseName: String?): MedicineModel =
    MedicineModel(
        id = this.id ?: -1,
        diseaseName = diseaseName ?: "",
        medicineDose = this.dose ?: "",
        medicineName = this.name ?: "",
        medicineStrength = this.strength ?: "",
    )

fun Medication.toMedicine(diseaseName: String?): Medicine =
    Medicine(
        id = this.id ?: -1,
        diseaseName = diseaseName ?: "",
        medicineDose = this.dose ?: "",
        medicineName = this.name ?: "",
        medicineStrength = this.strength ?: "",
    )

fun MedicineModel.toMedicine(): Medicine =
    Medicine(
        id = this.id,
        diseaseName = diseaseName,
        medicineDose = this.medicineDose,
        medicineName = this.medicineName,
        medicineStrength = this.medicineStrength,
    )