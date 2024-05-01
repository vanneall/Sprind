package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.point.sprind.entity.viewholder.product.cart.CartProductViewHolder

class CartItemDecorator : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildViewHolder(view)) {
            is CartProductViewHolder -> {}
            else -> outRect.top = 8.toDp(view)
        }
    }
}