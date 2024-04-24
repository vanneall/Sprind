package ru.point.sprind

interface ThemeSwitcher {
    fun switchTheme(isDark: Boolean)
    val isDark: Boolean
}