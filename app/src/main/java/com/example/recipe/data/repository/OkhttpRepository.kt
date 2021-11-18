package com.example.recipe.data.repository

import com.example.recipe.data.api.RecipesApi
import com.example.recipe.domain.BaseRepository
import com.example.recipe.models.converter.Converter
import com.example.recipe.models.data.Recipe
import com.example.recipe.models.domain.RecipeDomainModel
import javax.inject.Inject

/**
 * Реализация [BaseRepository]
 *
 * @param recipesApi апи для работы с сервераными данными
 */

class OkhttpRepository @Inject constructor(private val recipesApi: RecipesApi, private val converter: Converter<Recipe, RecipeDomainModel>): BaseRepository {

    override fun get(query: String): List<RecipeDomainModel> {
        return recipesApi.get(query).map(converter::convert)
    }
}