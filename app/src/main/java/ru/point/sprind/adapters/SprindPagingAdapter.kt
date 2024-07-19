package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class SprindPagingAdapter(
    private val delegates: List<Delegate<*>>,
    itemsComparator: (ViewObject, ViewObject) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    contentComparator: (ViewObject, ViewObject) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    changePayload: (ViewObject, ViewObject) -> Any? = { _, _ -> null }
) : PagingDataAdapter<ViewObject, ViewHolderV2<ViewObject>>(
    DiffUtilCallback(
        itemsComparator,
        contentComparator,
        changePayload
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderV2<ViewObject> {
        return delegates[viewType]
            .createViewHolder(parent)
            .run { this as ViewHolderV2<ViewObject> }
    }

    override fun onBindViewHolder(holder: ViewHolderV2<ViewObject>, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onBindViewHolder(
        holder: ViewHolderV2<ViewObject>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else holder.bindWithPayload(getItem(position) ?: return, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { delegate ->
            getItem(position)?.let { delegate.isSupported(it) } ?: false
        }
    }
}