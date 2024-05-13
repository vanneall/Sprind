package ru.point.sprind.adapters.decorators.spans

import androidx.recyclerview.widget.GridLayoutManager
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.deletage.product.favorites.EmptyFavoritesDelegate

class FavoriteSpanSizeLookup(
    private val delegates: List<Delegate<*>>,
    private val adapter: MordaAdapter
) : GridLayoutManager.SpanSizeLookup() {

    override fun getSpanSize(position: Int): Int {
        val delegate = delegates[adapter.getItemViewType(position)]
        return if (delegate is EmptyFavoritesDelegate) 2 else 1
    }
}