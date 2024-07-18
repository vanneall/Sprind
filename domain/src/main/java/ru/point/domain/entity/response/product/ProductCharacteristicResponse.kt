package ru.point.domain.entity.response.product

import com.google.gson.annotations.SerializedName
import ru.point.domain.entity.response.ResponseItem

data class ProductCharacteristicResponse(
    @SerializedName("name")
    val sectionTitle: String,
    @SerializedName("description")
    private val allDescriptions: String,
) : ResponseItem {
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