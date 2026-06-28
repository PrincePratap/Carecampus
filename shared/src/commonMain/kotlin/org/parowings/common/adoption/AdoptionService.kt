package org.parowings.common.adoption

internal interface AdoptionService {
    suspend fun createAdoption(request: AdoptionRequest): AdoptionResponse

    suspend fun applyForAdoption(request: ApplyForAdoptionRequest): ApplyForAdoptionResponse

    suspend fun getAdoptions(
        city: String? = null,
        animalType: String? = null
    ): List<AdoptionResponse>

    suspend fun getAdoptionById(id: String): AdoptionResponse

    suspend fun getMyAdoptions(ownerId: String): MyAdoptionsResponse
}
