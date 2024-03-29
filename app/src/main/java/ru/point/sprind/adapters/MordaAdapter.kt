package ru.point.sprind.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.sprind.entity.deletage.Delegate

class MordaAdapter(
    private val delegates: List<Delegate>,
    var views: List<ru.point.domain.entity.ListView>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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