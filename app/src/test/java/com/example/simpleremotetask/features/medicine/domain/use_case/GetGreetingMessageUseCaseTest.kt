package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.utils.Constants.GOOD_AFTERNOON
import com.example.simpleremotetask.utils.Constants.GOOD_EVENING
import com.example.simpleremotetask.utils.Constants.GOOD_MORNING
import com.example.simpleremotetask.utils.Constants.HELLO
import org.junit.Assert
import org.junit.Test

class GetGreetingMessageUseCaseTest {

    private var getGreetingMessageUseCase = GetGreetingMessageUseCase()

    @Test
    fun testGoodMorningWhenTimeIsZero() {
        val result = getGreetingMessageUseCase(0)
        Assert.assertEquals(result, GOOD_MORNING)
    }

    @Test
    fun testGoodMorningWhenTimeBetweenZeroAndEleven() {
        val result = getGreetingMessageUseCase(5)
        Assert.assertEquals(result, GOOD_MORNING)
    }

    @Test
    fun testGoodMorningWhenTimeIsEleven() {
        val result = getGreetingMessageUseCase(11)
        Assert.assertEquals(result, GOOD_MORNING)
    }


    @Test
    fun testGoodAfterNoonWhenTimeIsTwelve() {
        val result = getGreetingMessageUseCase(12)
        Assert.assertEquals(result, GOOD_AFTERNOON)
    }

    @Test
    fun testGoodAfterNoonWhenTimeBetweenTwelveAndSixten() {
        val result = getGreetingMessageUseCase(14)
        Assert.assertEquals(result, GOOD_AFTERNOON)
    }

    @Test
    fun testGoodAfterNoonWhenTimeIsSixten() {
        val result = getGreetingMessageUseCase(16)
        Assert.assertEquals(result, GOOD_AFTERNOON)
    }


    @Test
    fun testGoodEveningNoonWhenTimeIsSeventeen() {
        val result = getGreetingMessageUseCase(17)
        Assert.assertEquals(result, GOOD_EVENING)
    }

    @Test
    fun testGoodAfterNoonWhenTimeBetweenSeventeenAndTwentyThree() {
        val result = getGreetingMessageUseCase(20)
        Assert.assertEquals(result, GOOD_EVENING)
    }

    @Test
    fun testGoodEveningNoonWhenTimeIsTwentyThree() {
        val result = getGreetingMessageUseCase(23)
        Assert.assertEquals(result, GOOD_EVENING)
    }


    @Test
    fun testHelloWhenInvalidNumberBeingSent() {
        val result = getGreetingMessageUseCase(24)
        Assert.assertEquals(result, HELLO)
    }

}