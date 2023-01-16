package com.example.simpleremotetask.features.medicine.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MedicineModel.TABLE_NAME)
data class MedicineModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val diseaseName:String,
    val medicineName: String,
    val medicineDose: String,
    val medicineStrength: String
){
    companion object {
        const val TABLE_NAME = "medicines"
    }
}
