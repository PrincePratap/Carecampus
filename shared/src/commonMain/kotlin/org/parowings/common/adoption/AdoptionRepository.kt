package org.parowings.common.adoption

import org.parowings.common.data.remote.Result

interface AdoptionRepository {
    suspend fun createAdoption(request: AdoptionRequest): Result<AdoptionResponse>

    suspend fun applyForAdoption(request: ApplyForAdoptionRequest): Result<ApplyForAdoptionResponse>

    suspend fun getAdoptions(city: String? = null, animalType: String? = null): Result<List<AdoptionResponse>>

    suspend fun getAdoptionById(id: String): Result<AdoptionResponse>

    suspend fun getMyAdoptions(ownerId: String): Result<MyAdoptionsResponse>
}
