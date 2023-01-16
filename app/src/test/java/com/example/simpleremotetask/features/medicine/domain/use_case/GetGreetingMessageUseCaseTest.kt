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
    fun testGoodMorningWhenTimeIsFive() {
        val result = getGreetingMessageUseCase(5)
        Assert.assertEquals(result, GOOD_MORNING)
    }

    @Test
    fun testGoodMorningWhenTimeBetweenFiveAndEleven() {
        val result = getGreetingMessageUseCase(9)
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
    fun testGoodAfterNoonWhenTimeBetweenTwelveAndEighteen() {
        val result = getGreetingMessageUseCase(14)
        Assert.assertEquals(result, GOOD_AFTERNOON)
    }

    @Test
    fun testGoodAfterNoonWhenTimeIsEighteen() {
        val result = getGreetingMessageUseCase(18)
        Assert.assertEquals(result, GOOD_AFTERNOON)
    }


    @Test
    fun testGoodEveningNoonWhenTimeIsNineteen() {
        val result = getGreetingMessageUseCase(19)
        Assert.assertEquals(result, GOOD_EVENING)
    }

    @Test
    fun testGoodAfterNoonWhenTimeBetweenNineteenAndTwentyThree() {
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