package ru.point.sprind.adapters.decorators.spans

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.product.favorites.EmptyFavoritesDelegate

class FavoriteSpanSizeLookup(
    private val delegates: List<Delegate<*>>,
    private val adapter: RecyclerView.Adapter<*>
) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        val delegate = delegates[adapter.getItemViewType(position)]
        return if (delegate is EmptyFavoritesDelegate) 2 else 1
    }
}