package com.example.simpleremotetask.features.medicine.data.mapper

import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import org.junit.Assert
import org.junit.Test


class MedicineMapperKtTest {

    private val medication = Medication(
        id = 1,
        dose = "dose",
        name = "name",
        strength = "strength"
    )
    private val disease = "disease"

    private val medicineModel = MedicineModel(
        id = 1,
        medicineDose = "dose",
        diseaseName = disease,
        medicineName = "name",
        medicineStrength = "strength"
    )


    @Test
    fun medicationToMedicineModel() {
        val medicineModel = medication.toMedicine(disease)
        Assert.assertEquals(medicineModel.id, medication.id)
        Assert.assertEquals(medicineModel.diseaseName, disease)
        Assert.assertEquals(medicineModel.medicineDose, medication.dose)
        Assert.assertEquals(medicineModel.medicineStrength, medication.strength)
        Assert.assertEquals(medicineModel.medicineName, medication.name)
    }

    @Test
    fun medicationToMedicine() {
        val medicineModel: MedicineModel = medication.toMedicineModel(disease)
        Assert.assertEquals(medicineModel.id, medication.id)
        Assert.assertEquals(medicineModel.diseaseName, disease)
        Assert.assertEquals(medicineModel.medicineDose, medication.dose)
        Assert.assertEquals(medicineModel.medicineStrength, medication.strength)
        Assert.assertEquals(medicineModel.medicineName, medication.name)
    }

    @Test
    fun medicineModelToMedicine() {
        val medicineModel = medicineModel.toMedicine()
        Assert.assertEquals(medicineModel.id, medication.id)
        Assert.assertEquals(medicineModel.diseaseName, disease)
        Assert.assertEquals(medicineModel.medicineDose, medication.dose)
        Assert.assertEquals(medicineModel.medicineStrength, medication.strength)
        Assert.assertEquals(medicineModel.medicineName, medication.name)
    }
}