package com.catvasiliy.domain.repository

import com.catvasiliy.domain.model.client.Client
import com.catvasiliy.domain.model.client.NewClient

interface ClientRepository {
    suspend fun getClientsList(): List<Client>
    suspend fun getClientById(id: Int): Client
    suspend fun createClient(client: NewClient)
}