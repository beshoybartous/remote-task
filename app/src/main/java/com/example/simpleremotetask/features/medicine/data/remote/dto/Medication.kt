package com.example.simpleremotetask.features.medicine.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class Medication(
    var id: Int?,
    var dose: String?,
    var name: String?,
    var strength: String?
)