package com.catvasiliy.domain.repository

import com.catvasiliy.domain.model.Client

interface ClientRepository {
    suspend fun getClientsList(): List<Client>
    suspend fun getClientById(id: Int): Client
    suspend fun createClient(client: Client)
}