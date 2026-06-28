package org.parowings.common.adoption

import kotlinx.coroutines.withContext
import org.parowings.common.data.remote.Result
import org.parowings.common.util.DispatcherProvider

internal class AdoptionRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val adoptionService: AdoptionService
) : AdoptionRepository {

    override suspend fun createAdoption(request: AdoptionRequest): Result<AdoptionResponse> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(adoptionService.createAdoption(request))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun applyForAdoption(request: ApplyForAdoptionRequest): Result<ApplyForAdoptionResponse> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(adoptionService.applyForAdoption(request))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getAdoptions(
        city: String?,
        animalType: String?
    ): Result<List<AdoptionResponse>> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(adoptionService.getAdoptions(city, animalType))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getAdoptionById(id: String): Result<AdoptionResponse> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(adoptionService.getAdoptionById(id))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getMyAdoptions(ownerId: String): Result<MyAdoptionsResponse> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(adoptionService.getMyAdoptions(ownerId))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }
}
