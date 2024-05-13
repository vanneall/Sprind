package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.point.sprind.entity.viewholder.product.favorites.EmptyFavoritesViewHolder

class FavoritesItemDecorator : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewHolder = parent.getChildViewHolder(view)
        if (viewHolder is EmptyFavoritesViewHolder) {
            outRect.top = 8.toDp(view);
            (parent.layoutManager as GridLayoutManager).spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 2
                }
            }
        } else {
            val index = parent.getChildLayoutPosition(view)
            val px = 18.toDp(view)

            val isLeft = (index % 2 == 0)
            outRect.set(
                if (isLeft) px else px / 2,
                0,
                if (isLeft) px / 2 else px,
                px
            )
        }

    }
}