package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName

data class ProductCharacteristicDto(
    @SerializedName("name")
    val sectionTitle: String,
    @SerializedName("description")
    private val allDescriptions: String,
) {
    val descriptions: List<Pair<String, String>>
        get() {
            val list = mutableListOf<Pair<String, String>>()
            allDescriptions
                .split(";")
                .filter { s -> s.isNotEmpty() }
                .forEach { s ->
                    list.add(Pair(s.substringBefore(':'), s.substringAfter(':')))
                }
            return list
        }
}