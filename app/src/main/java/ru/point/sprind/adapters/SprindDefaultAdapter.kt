package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class SprindDefaultAdapter(
    private val delegates: List<Delegate<*>>,
) : ListAdapter<ViewObject, ViewHolderV2<ViewObject>>(
    AsyncDifferConfig.Builder(DiffUtilCallback<ViewObject>()).build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderV2<ViewObject> {
        return delegates[viewType]
            .createViewHolder(parent)
            .run { this as ViewHolderV2<ViewObject> }
    }

    override fun onBindViewHolder(holder: ViewHolderV2<ViewObject>, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { delegate -> delegate.isSupported(currentList[position]) }
    }
}