package com.example.simpleremotetask.features.medicine.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class DiseasesDto(
    var problems: List<Problem?>?
)