package com.example.simpleremotetask.features.medicine.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class Problem(
    var id: String?,
    var medications: List<Medication?>?
)