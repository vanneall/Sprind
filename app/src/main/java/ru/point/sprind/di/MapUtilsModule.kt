package ru.point.sprind.di

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.runtime.image.ImageProvider
import dagger.Module
import dagger.Provides
import ru.point.sprind.R

@Module
class MapUtilsModule {

    @Provides
    fun provideFusedLocationProviderClient(context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideMapIconBitmap(context: Context): ImageProvider {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_pin_red)
            ?: throw RuntimeException("Marker resource is missed")

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return ImageProvider.fromBitmap(bitmap)
    }
}