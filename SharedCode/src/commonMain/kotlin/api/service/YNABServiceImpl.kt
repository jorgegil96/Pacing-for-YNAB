package com.farsidelabs.pacingforynab.api.service

import com.farsidelabs.pacingforynab.api.base.Response
import com.farsidelabs.pacingforynab.api.entities.budget.BudgetEntity
import com.farsidelabs.pacingforynab.api.entities.budget.BudgetsResponseEntity
import com.farsidelabs.pacingforynab.api.entities.user.UserEntity
import com.farsidelabs.pacingforynab.api.entities.user.UserResponseEntity
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class YNABServiceImpl : YNABService {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
            accept(ContentType.Application.Json)
            accept(ContentType.Text.Html)
        }
        expectSuccess = false
    }

    override suspend fun listBudgets(): Response<List<BudgetEntity>> {
        return boxedRequest(httpClient.get<BudgetsResponseEntity> {
            apiUrl(path = "budgets")
        }.data.budgets)
    }

    override suspend fun getUser(): Response<UserEntity> {
        return boxedRequest(httpClient.get<UserResponseEntity> {
            apiUrl(path = "user")
        }.data.user)
    }

    private fun <T> boxedRequest(request: T): Response<T> {
        return try {
            Response.Success(request)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        header(HttpHeaders.Authorization, "Bearer $TOKEN")
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(API_URL)
            path("v1", path)
        }
    }

    companion object {
        private const val API_URL = "https://api.youneedabudget.com"
        private const val TOKEN = "TODO: use real token"
    }
}