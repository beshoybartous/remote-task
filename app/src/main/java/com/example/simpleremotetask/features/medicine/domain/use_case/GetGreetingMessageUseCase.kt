package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.utils.Constants.GOOD_AFTERNOON
import com.example.simpleremotetask.utils.Constants.GOOD_EVENING
import com.example.simpleremotetask.utils.Constants.GOOD_MORNING
import com.example.simpleremotetask.utils.Constants.HELLO
import javax.inject.Inject

class GetGreetingMessageUseCase @Inject constructor() {
    operator fun invoke(time: Int) = when (time) {
        in 0..11 -> GOOD_MORNING
        in 12..16 -> GOOD_AFTERNOON
        in 17..23 -> GOOD_EVENING
        else -> HELLO
    }
}