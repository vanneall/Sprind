package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalFavoritesItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val dp = 20.toDp(view)
        outRect.set(dp, 0, 0, 0)
    }
}