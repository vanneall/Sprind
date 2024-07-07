package ru.point.domain.exceptions

class ViewObjectMapRuleNotFoundException(message: String) : RuntimeException(message) {
    constructor(): this("Mapper for view not found")
}