package ru.point.sprind.adapters.decorators

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.entity.viewholder.product.card.CharacteristicSectionViewHolder
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder
import ru.point.sprind.entity.viewholder.product.card.CharacteristicViewHolder

class InfoProductDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (val viewHolder = parent.getChildViewHolder(view)) {
            is CharacteristicSectionViewHolder -> {}
            is CharacteristicViewHolder -> {}
            is CharacteristicTitleViewHolder -> {}
            else -> {
                outRect.bottom = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    8f,
                    view.context.resources.displayMetrics
                ).toInt()
            }
        }
    }
}