package ru.point.sprind.components

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.yandex.mapkit.MapKitFactory
import ru.point.sprind.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor =
            ContextCompat.getColor(this, ru.point.sprind.R.color.md_theme_surfaceVariant)

        MapKitFactory.setApiKey("f425d115-72d7-4ff9-bff7-20f64fbd8769")
        MapKitFactory.initialize(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(ru.point.sprind.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(
            navigationBarView = binding.bottomNavigation,
            navController = Navigation.findNavController(this, binding.frame.id)
        )
    }
}