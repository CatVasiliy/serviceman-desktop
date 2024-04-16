package com.catvasiliy.data

import com.catvasiliy.domain.model.Client
import com.catvasiliy.domain.repository.ClientRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientRepositoryImpl(private val httpClient: HttpClient) : ClientRepository {

    override suspend fun getClientsList(): List<Client> = withContext(Dispatchers.IO) {
        httpClient.get("clients").body<List<Client>>()
    }

    override suspend fun getClientById(id: Int): Client = withContext(Dispatchers.IO) {
        httpClient.get("clients/$id").body<Client>()
    }

    override suspend fun createClient(client: Client): Unit = withContext(Dispatchers.IO) {
        httpClient.post("clients") {
            contentType(ContentType.Application.Json)
            setBody(client)
        }
    }
}