package ru.point.sprind.adapters.decorators

import android.util.TypedValue
import android.view.View

fun Int.toDp(view: View): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        view.context.resources.displayMetrics
    ).toInt()
}