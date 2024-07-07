package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class SprindPagingAdapter(
    private val delegates: List<Delegate<*>>,
) : PagingDataAdapter<ViewObject, ViewHolderV2<ViewObject>>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderV2<ViewObject> {
        return delegates[viewType]
            .createViewHolder(parent)
            .run { this as ViewHolderV2<ViewObject> }
    }

    override fun onBindViewHolder(holder: ViewHolderV2<ViewObject>, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { delegate ->
            getItem(position)?.let { delegate.isSupported(it) } ?: false
        }
    }
}