package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.entity.deletage.Delegate

class MordaAdapter(
    private val delegates: List<Delegate>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var views: List<ListView> = emptyList()
        set(new) {
            val callback = DiffUtilCallback<ListView>(
                oldList = field,
                newList = new
            )
            field = new
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].createViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].bindViewHolder(
            viewHolder = holder,
            view = views[position]
        )
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { delegate -> delegate.forItem(views[position]) }
    }

    override fun getItemCount(): Int = views.size
}