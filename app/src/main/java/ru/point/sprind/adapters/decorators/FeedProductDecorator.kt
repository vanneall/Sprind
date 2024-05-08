package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedProductDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildLayoutPosition(view)
        val px = 18.toDp(view)

        val isLeft = (index % 2 == 0)
        outRect.set(
            if (isLeft) px else px/2,
            0,
            if (isLeft) px/2 else px,
            px
        )
    }
}

