package ru.point.sprind

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import ru.point.sprind.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ThemeSwitcher, RequestManager {

    lateinit var binding: ActivityMainBinding

    private var isDarkTheme: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeTheme()
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(
            navigationBarView = binding.bottomNavigation,
            navController = Navigation.findNavController(this, binding.frame.id)
        )
    }

    override fun switchTheme(isDark: Boolean) {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

        val isDarkThemeSelected = sharedPreferences.getBoolean(DARK_THEME_IS_DARK, false)

        sharedPreferences.edit().putBoolean(DARK_THEME_IS_DARK, !isDarkThemeSelected).apply()
        applyTheme(!isDarkThemeSelected)
    }

    private fun initializeTheme() {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val isDarkThemeSelected = sharedPreferences.getBoolean(DARK_THEME_IS_DARK, false)
        isDarkTheme = isDarkThemeSelected
        applyTheme(isDarkThemeSelected)
    }

    private fun applyTheme(isDark: Boolean) {
        val mode = if (isDark) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override val isDark: Boolean
        get() = isDarkTheme

    override fun getRequestsHistory(): List<ru.point.domain.entity.Request> {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val requests = sharedPreferences
            .getString(REQUESTS, "")
            ?.split(";")
            ?: emptyList()

        return requests
            .filter { it != "" }
            .map { ru.point.domain.entity.Request(id = 1, text = it) }
    }

    override fun addRequest(request: Request) {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val requests = sharedPreferences.getString(REQUESTS, "") ?: ""
        val newRequest = listOf(request) + requests.split(";")
        sharedPreferences.edit().putString(REQUESTS, newRequest.take(10).joinToString(";")).apply()
    }

    companion object {
        private const val PREFERENCES = "Preferences"
        private const val REQUESTS = "REQUESTS"
        private const val DARK_THEME_IS_DARK = "DARK_THEME_IS_DARK"
    }
}