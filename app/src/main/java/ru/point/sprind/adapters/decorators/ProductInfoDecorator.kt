package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder
import ru.point.sprind.entity.viewholder.product.card.CharacteristicViewHolder
import ru.point.sprind.entity.viewholder.product.card.ProductImageViewHolder

class ProductInfoDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (val viewHolder = parent.getChildViewHolder(view)) {
            is ProductImageViewHolder -> {}
            is CharacteristicViewHolder -> {}
            is CharacteristicTitleViewHolder -> {}
            else -> {
                outRect.top = 8.toDp(view)
            }
        }
    }
}