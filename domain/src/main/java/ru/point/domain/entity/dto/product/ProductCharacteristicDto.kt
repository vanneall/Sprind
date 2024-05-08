package ru.point.domain.entity.dto.product

import com.google.gson.annotations.SerializedName

data class ProductCharacteristicDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    private val description: String,
) {
    val elements: List<Pair<String, String>>
        get() {
            val list = mutableListOf<Pair<String, String>>()
            description
                .split(";")
                .filter { s -> s.isNotEmpty() }
                .forEach { s ->
                    list.add(Pair(s.substringBefore(':'), s.substringAfter(':')))
                }
            return list
        }
}