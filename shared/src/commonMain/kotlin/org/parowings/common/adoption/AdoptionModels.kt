package org.parowings.common.adoption

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable




@Serializable
data class ApplyForAdoptionResponse(
    val message: String,

    val id: String,

    @SerialName("created_at")
    val createdAt: String,

    val status: String,

    @SerialName("animal_id")
    val animalId: String,

    @SerialName("user_id")
    val userId: String
)

@Serializable
data class AdoptionRequest(
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("owner_name")
    val ownerName: String,
    @SerialName("animal_name")
    val animalName: String,
    @SerialName("animal_type")
    val animalType: String,
    @SerialName("breed")
    val breed: String,
    @SerialName("age")
    val age: Int,
    @SerialName("gender")
    val gender: String,
    @SerialName("description")
    val description: String,
    @SerialName("vaccinated")
    val vaccinated: Boolean,
    @SerialName("sterilized")
    val sterilized: Boolean,
    @SerialName("photo_url")
    val photoUrl: String,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String,
    @SerialName("contact_number")
    val contactNumber: String
)

@Serializable
data class AdoptionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("animal_name")
    val animalName: String,
    @SerialName("breed")
    val breed: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("vaccinated")
    val vaccinated: Boolean,
    @SerialName("adoption_status")
    val adoptionStatus: String,
    @SerialName("city")
    val city: String,
    @SerialName("contact_number")
    val contactNumber: String,
    @SerialName("animal_type")
    val animalType: String,
    @SerialName("owner_name")
    val ownerName: String,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("age")
    val age: Int,
    @SerialName("description")
    val description: String,
    @SerialName("sterilized")
    val sterilized: Boolean,
    @SerialName("photo_url")
    val photoUrl: String,
    @SerialName("state")
    val state: String,
    @SerialName("created_at")
    val createdAt: String
)

@Serializable
data class MyAdoptionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("animal_name")
    val animalName: String,
    @SerialName("animal_type")
    val animalType: String,
    @SerialName("photo_url")
    val photoUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String,
    @SerialName("owner_name")
    val ownerName: String,
    @SerialName("breed")
    val breed: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("vaccinated")
    val vaccinated: Boolean,
    @SerialName("sterilized")
    val sterilized: Boolean,
    @SerialName("contact_number")
    val contactNumber: String,
    @SerialName("adoption_status")
    val adoptionStatus: String
)

@Serializable
data class MyAdoptionsResponse(
    @SerialName("owner_id")
    val ownerId: String,

    @SerialName("total_posts")
    val totalPosts: Int,

    @SerialName("adoptions")
    val adoptions: List<MyAdoptionResponse>
)

@Serializable
data class AdoptionListResponse(
    @SerialName("success")
    val success: Boolean,

    @SerialName("message")
    val message: String,

    @SerialName("total")
    val total: Int,

    @SerialName("data")
    val data: List<AdoptionResponse>
)
@Serializable
data class ApplyForAdoptionRequest(
    @SerialName("animal_id")
    val animalId: String,

    @SerialName("user_id")
    val userId: String,

    val message: String
)