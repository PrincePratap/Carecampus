package org.parowings.common.adoption

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody

internal class AdoptionServiceImpl(private val client: HttpClient) : AdoptionService {

    override suspend fun createAdoption(request: AdoptionRequest): AdoptionResponse {
        return client.post("/adoptions") {
            setBody(request)
        }.body()
    }

    override suspend fun applyForAdoption(request: ApplyForAdoptionRequest): ApplyForAdoptionResponse {
        return client.post("/adoption-requests") {
            setBody(request)
        }.body()
    }




    override suspend fun getAdoptions(
        city: String?,
        animalType: String?
    ): List<AdoptionResponse> {
        val response = client.get("/adoptions") {
            if (city != null) {
                parameter("city", city)
            }
            if (animalType != null) {
                parameter("animal_type", animalType)
            }
        }.body<AdoptionListResponse>()
        return response.data
    }

    override suspend fun getAdoptionById(id: String): AnimalResponseDetail {
        return client.post("/adoptions/$id").body()
    }

    override suspend fun getMyAdoptions(ownerId: String): MyAdoptionsResponse {
        return client.get("/adoptions/my/$ownerId").body()
    }
}
