package com.example.recipe

import com.example.recipe.data.api.OkHttpRecipesApiImpl
import com.example.recipe.data.repository.OkhttpRepository
import com.example.recipe.models.converter.Converter
import com.example.recipe.models.converter.RecipeToDomainConverter
import com.example.recipe.models.data.Recipe
import com.example.recipe.models.domain.RecipeDomainModel
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Before
import java.lang.Exception

class OkHttpRepositoryTest {
    private val api: OkHttpRecipesApiImpl = mockk()
    private lateinit var converter: Converter<Recipe, RecipeDomainModel>
    private lateinit var repository: OkhttpRepository

    @Before
    fun setUp() {
        converter = RecipeToDomainConverter()
        repository = OkhttpRepository(api, converter)
    }

    @Test
    fun getTest() {
        val apiResult: List<Recipe> = listOf(Recipe("uri", "label", "image", "source", "url", listOf("ingredientLines")))
        val expectedResult: List<RecipeDomainModel> = listOf(RecipeDomainModel("uri", "label", "image", "source", "url", listOf("ingredientLines")))
        every { api.get(queryArgument) } returns apiResult

        val testResult = repository.get(queryArgument)

        Truth.assertThat(testResult).isEqualTo(expectedResult)
    }

    @Test
    fun getTestException() {

        every { api.get(queryArgumentForException) } throws Exception()

        var testResult = false
        try {
            repository.get(queryArgumentForException)
        } catch (e: Exception) {
            testResult = true
        }

        Truth.assertThat(testResult).isTrue()
    }

    private companion object {
        const val queryArgument = "Nyama"
        const val queryArgumentForException = "nyam"
    }
}