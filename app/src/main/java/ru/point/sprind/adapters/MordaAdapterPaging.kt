package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class MordaAdapterPaging(
    private val delegates: List<Delegate<*>>,
) : PagingDataAdapter<ViewObject, ViewHolderV2<ViewObject>>(COMPARATOR) {

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

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ViewObject>() {
            override fun areItemsTheSame(oldItem: ViewObject, newItem: ViewObject): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ViewObject, newItem: ViewObject): Boolean {
                return false
            }

            override fun getChangePayload(oldItem: ViewObject, newItem: ViewObject): Any? {
                return null
            }
        }
    }
}